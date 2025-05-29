package org.reporting.service.impl;


import com.abb.pelerinage.reporting.dto.PaginationDto;
import com.abb.pelerinage.reporting.dto.ReglementPelerinageDto;
import com.abb.pelerinage.reporting.dto.ReglementsPelerinageRequest;
import com.abb.pelerinage.reporting.dto.ReglementsPelerinageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reporting.entity.PelPaiement;
import org.reporting.entity.PelPaiementWithDetails;
import org.reporting.mapper.ReglementMapper;
import org.reporting.repository.ConsultationReglementRepository;
import org.reporting.service.ReglementsPelerinageService;
import org.reporting.specification.ReglementSpecification;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReglementsPelerinageServiceImpl implements ReglementsPelerinageService {

    private final ConsultationReglementRepository paiementRepository;
    private final ReglementMapper reglementMapper;

    @Override
    public ReglementsPelerinageResponse consulterReglements(ReglementsPelerinageRequest request) {

        log.info("Consultation règlements - Période: {} à {}, Statut: {}",
                request.getDateDebut(), request.getDateFin(), request.getStatut());

        // Configuration de la pagination
        Pageable pageable = PageRequest.of(
                request.getPage() != null ? request.getPage() : 0,
                request.getSize() != null ? request.getSize() : 20,
                Sort.by(Sort.Direction.DESC, "datePaiement")
        );

        // Conversion des dates
        LocalDateTime dateDebut = request.getDateDebut().atStartOfDay();
        LocalDateTime dateFin = request.getDateFin().atTime(23, 59, 59);

        // Mapping du statut
        String statutBD = mapStatutToBD(request.getStatut().toString());

        // Recherche avec jointures
        Page<PelPaiement> paiementsPage = paiementRepository.findReglementsPelerinageWithDetails(
                dateDebut, dateFin, statutBD, pageable
        );

        log.info("Règlements trouvés: {} sur {} total",
                paiementsPage.getNumberOfElements(), paiementsPage.getTotalElements());

        // Mapping vers DTO
        List<ReglementPelerinageDto> reglements = paiementsPage.getContent()
                .stream()
                .map(reglementMapper::toDto)
                .collect(Collectors.toList());

        return ReglementsPelerinageResponse.builder()
                .reglements(reglements)
                .pagination(PaginationDto.builder()
                        .totalElements(paiementsPage.getTotalElements())
                        .totalPages(paiementsPage.getTotalPages())
                        .currentPage(paiementsPage.getNumber())
                        .build())
                .build();
    }

    @Override
    public Resource exporterReglementsExcel(ReglementsPelerinageRequest request) {
        return null;
    }

    private String mapStatutToBD(String statutSwagger) {
        switch (statutSwagger.toUpperCase()) {
            case "VALIDE":
                return "PAYE";
            case "ANNULE":
                return "ANNULE";
            default:
                return statutSwagger;
        }
    }
}