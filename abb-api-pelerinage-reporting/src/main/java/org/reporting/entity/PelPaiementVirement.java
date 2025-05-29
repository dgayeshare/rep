package org.reporting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;

@Entity
@Table(name = "pel_paiement_virement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PelPaiementVirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paiement_id")
    private Integer paiementId;

    @Column(name = "rib_compte", nullable = false, length = 100)
    private String ribCompte;

    @Column(name = "intitule_compte", length = 200)
    private String intituleCompte;

    @Column(name = "montant", nullable = false, precision = 12, scale = 2)
    private BigDecimal montant;

}