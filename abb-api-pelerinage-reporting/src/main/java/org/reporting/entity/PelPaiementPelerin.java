package org.reporting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;

@Entity
@Table(name = "pel_paiement_pelerin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PelPaiementPelerin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paiement_id")
    private Long paiementId;

    @Column(name = "pelerin_id")
    private Long pelerinId;

    @Column(name = "montant_global")
    private BigDecimal montantGlobal;

    @Column(name = "montant_verse")
    private BigDecimal montantVerse;

    @Column(name = "agence_id")
    private Integer agenceId;

    // Relations avec les entités principales
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paiement_id", insertable = false, updatable = false)
    private PelPaiement paiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelerin_id", insertable = false, updatable = false)
    private Pelerin pelerin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agence_id", insertable = false, updatable = false)
    private AgenceVoyage agence;

}