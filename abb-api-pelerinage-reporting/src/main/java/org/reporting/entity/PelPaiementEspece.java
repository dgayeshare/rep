package org.reporting.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "pel_paiement_espece")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PelPaiementEspece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paiement_id", nullable = false)
    private Integer paiementId;

    @Column(name = "montant_encaisse", nullable = false, precision = 12, scale = 2)
    private BigDecimal montantEncaisse;

}