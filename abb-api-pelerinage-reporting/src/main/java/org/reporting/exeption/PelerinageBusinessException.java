package org.reporting.exeption;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;


@Getter
public class PelerinageBusinessException extends RuntimeException {

    private final String errorCode;
    private final String details;
    private final HttpStatus httpStatus;

    public PelerinageBusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.details = null;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public PelerinageBusinessException(String errorCode, String message, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public PelerinageBusinessException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.details = null;
        this.httpStatus = httpStatus;
    }

    public PelerinageBusinessException(String errorCode, String message, String details, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
        this.httpStatus = httpStatus;
    }

    public PelerinageBusinessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.details = null;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    // Méthodes statiques pour créer facilement des exceptions courantes
    public static PelerinageBusinessException dateRangeInvalid(String details) {
        return new PelerinageBusinessException(
                "INVALID_DATE_RANGE",
                "La plage de dates spécifiée est invalide",
                details
        );
    }

    public static PelerinageBusinessException accessDenied(String resource) {
        return new PelerinageBusinessException(
                "ACCESS_DENIED",
                "Accès refusé à la ressource: " + resource,
                HttpStatus.FORBIDDEN
        );
    }

    public static PelerinageBusinessException exportLimitExceeded(int maxRecords) {
        return new PelerinageBusinessException(
                "EXPORT_LIMIT_EXCEEDED",
                "Le nombre d'enregistrements dépasse la limite autorisée",
                "Limite maximale: " + maxRecords
        );
    }

    public static PelerinageBusinessException invalidSearchCriteria(String message) {
        return new PelerinageBusinessException(
                "INVALID_SEARCH_CRITERIA",
                "Critères de recherche invalides",
                message
        );
    }
}