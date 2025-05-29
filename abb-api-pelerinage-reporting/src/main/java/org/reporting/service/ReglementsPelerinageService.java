package org.reporting.service;

import com.abb.pelerinage.reporting.dto.ReglementsPelerinageRequest;
import com.abb.pelerinage.reporting.dto.ReglementsPelerinageResponse;
import org.springframework.core.io.Resource;

public interface ReglementsPelerinageService {
    /**
     * Consulte les règlements de pèlerinage selon les critères
     */
    ReglementsPelerinageResponse consulterReglements(ReglementsPelerinageRequest request);

    /**
     * Exporte les règlements au format Excel
     */
    Resource exporterReglementsExcel(ReglementsPelerinageRequest request);
}