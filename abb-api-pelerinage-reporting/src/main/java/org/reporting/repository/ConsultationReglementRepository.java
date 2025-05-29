package org.reporting.repository;

import org.reporting.entity.PelPaiement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultationReglementRepository extends JpaRepository<PelPaiement, Long>,
        JpaSpecificationExecutor<PelPaiement> {

    @Query("""
    SELECT p FROM PelPaiement p
    LEFT JOIN FETCH p.paiementPelerins pp
    LEFT JOIN FETCH pp.pelerin pel
    LEFT JOIN FETCH pel.organisme org
    LEFT JOIN FETCH pel.encadrant pelEnc
    LEFT JOIN FETCH pel.province pelPro
    LEFT JOIN FETCH p.modePaiement mp
    LEFT JOIN FETCH p.encadrant enc
    WHERE p.datePaiement BETWEEN :dateDebut AND :dateFin
    AND (:statut IS NULL OR UPPER(p.statut) = UPPER(:statut))
    ORDER BY p.datePaiement DESC
    """)
    Page<PelPaiement> findReglementsPelerinageWithDetails(
            @Param("dateDebut") LocalDateTime dateDebut,
            @Param("dateFin") LocalDateTime dateFin,
            @Param("statut") String statut,
            Pageable pageable
    );
}