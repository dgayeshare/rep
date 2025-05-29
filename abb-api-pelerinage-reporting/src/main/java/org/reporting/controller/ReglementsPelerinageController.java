package org.reporting.controller;

import com.abb.pelerinage.reporting.api.EtatReglementApi;
import com.abb.pelerinage.reporting.dto.ReglementsPelerinageRequest;
import com.abb.pelerinage.reporting.dto.ReglementsPelerinageResponse;
import org.reporting.service.ReglementsPelerinageService;
import org.springframework.core.io.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class ReglementsPelerinageController implements EtatReglementApi {

    private final ReglementsPelerinageService reglementService;

    @Override
    public ResponseEntity<ReglementsPelerinageResponse> consulterReglementsPelerinage(@Valid ReglementsPelerinageRequest request) {

        log.info("Début consultation règlements pèlerinage - Request: {}", request);

        try {
            ReglementsPelerinageResponse response = reglementService.consulterReglements(request);
            log.info("Consultation terminée - {} règlements trouvés", response.getReglements().size());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);

        } catch (Exception e) {
            log.error("Erreur lors de la consultation des règlements", e);
            throw e; // Géré par GlobalExceptionHandler
        }
    }

    @Override
    public ResponseEntity<Resource> exporterReglementsPelerinage(@Valid ReglementsPelerinageRequest request) {

        log.info("Début export Excel règlements pèlerinage - Request: {}", request);

        try {
            Resource excelFile = reglementService.exporterReglementsExcel(request);

            String filename = String.format("reglements_pelerinage_%s_%s.xlsx",
                    request.getDateDebut(), request.getDateFin());

            log.info("Export Excel terminé - Fichier: {}", filename);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelFile);

        } catch (Exception e) {
            log.error("Erreur lors de l'export Excel des règlements", e);
            throw e; // Géré par GlobalExceptionHandler
        }
    }
}
