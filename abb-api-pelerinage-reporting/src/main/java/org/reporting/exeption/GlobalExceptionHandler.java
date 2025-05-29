package org.reporting.exeption;

import com.abb.pelerinage.reporting.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Gestion des erreurs de validation Bean Validation (@Valid sur les Request Bodies)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Erreur de validation des arguments: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorDto errorDto = ErrorDto.builder()
                .code("VALIDATION_ERROR")
                .message("Erreur de validation des données")
                .details(Collections.singletonList(errors.toString()))
                .timestamp(OffsetDateTime.from(Instant.now()))
                .build();

        return ResponseEntity.badRequest().body(errorDto);
    }

    /**
     * Gestion des erreurs de validation des contraintes (@Validated sur les classes)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn("Erreur de contrainte de validation: {}", ex.getMessage());

        String errors = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        ErrorDto errorDto = ErrorDto.builder()
                .code("CONSTRAINT_VIOLATION")
                .message("Violation des contraintes de validation")
                .details(Collections.singletonList(errors))
                .timestamp(OffsetDateTime.from(Instant.now()))
                .build();

        return ResponseEntity.badRequest().body(errorDto);
    }

    /**
     * Gestion des exceptions métier personnalisées
     */
    @ExceptionHandler(PelerinageBusinessException.class)
    public ResponseEntity<ErrorDto> handleBusinessException(PelerinageBusinessException ex) {
        log.error("Erreur métier - Code: {}, Message: {}", ex.getErrorCode(), ex.getMessage());

        ErrorDto errorDto = ErrorDto.builder()
                .code(ex.getErrorCode())
                .message(ex.getMessage())
                .details(Collections.singletonList(ex.getDetails()))
                .timestamp(OffsetDateTime.from(Instant.now()))
                .build();

        return ResponseEntity.status(ex.getHttpStatus()).body(errorDto);
    }

    /**
     * Gestion des erreurs de ressource non trouvée
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.warn("Ressource non trouvée: {}", ex.getMessage());

        ErrorDto errorDto = ErrorDto.builder()
                .code("RESOURCE_NOT_FOUND")
                .message(ex.getMessage())
                .details(Collections.singletonList(ex.getResourceType() + " avec l'identifiant: " + ex.getResourceId()))
                .timestamp(OffsetDateTime.from(Instant.now()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    /**
     * Gestion des erreurs d'accès aux données
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorDto> handleDataAccessException(DataAccessException ex) {
        log.error("Erreur d'accès aux données", ex);

        ErrorDto errorDto = ErrorDto.builder()
                .code("DATA_ACCESS_ERROR")
                .message("Erreur lors de l'accès aux données")
                .details(Collections.singletonList("Veuillez contacter l'administrateur système"))
                .timestamp(OffsetDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    /**
     * Gestion des erreurs non prévues
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception ex) {
        log.error("Erreur inattendue", ex);

        ErrorDto errorDto = ErrorDto.builder()
                .code("INTERNAL_SERVER_ERROR")
                .message("Une erreur inattendue s'est produite")
                .details(Collections.singletonList("Veuillez contacter l'administrateur système"))
                .timestamp(OffsetDateTime.from(Instant.now()))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}