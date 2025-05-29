package org.reporting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;

@Entity
@Table(name = "pel_paiement_cheque")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PelPaiementCheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paiement_id")
    private Long paiementId;

    @Column(name = "rib_compte", nullable = false, length = 100)
    private String ribCompte;

    @Column(name = "agence_domiciliataire", length = 100)
    private String agenceDomiciliataire;

    @Column(name = "intitule_compte", length = 200)
    private String intituleCompte;

    @Column(name = "numero_valeur", length = 50)
    private String numeroValeur;

    @Column(name = "montant", nullable = false, precision = 12, scale = 2)
    private BigDecimal montant;

    @Column(name = "statut_remise", length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'SAISIE'")
    @Builder.Default
    private String statutRemise = "SAISIE";

}
