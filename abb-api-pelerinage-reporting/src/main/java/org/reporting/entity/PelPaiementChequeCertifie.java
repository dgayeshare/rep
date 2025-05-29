package org.reporting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pel_paiement_cheque_certifie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PelPaiementChequeCertifie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paiement_id")
    private Integer paiementId;

    @Column(name = "agence_payeur", length = 100)
    private String agencePayeur;

    @Column(name = "date_certification")
    private LocalDate dateCertification;

    @Column(name = "ville", length = 100)
    private String ville;

    @Column(name = "rib_compte", nullable = false, length = 100)
    private String ribCompte;

    @Column(name = "serie_valeur", length = 50)
    private String serieValeur;

    @Column(name = "numero_valeur", length = 50)
    private String numeroValeur;

    @Column(name = "montant", nullable = false, precision = 12, scale = 2)
    private BigDecimal montant;
}