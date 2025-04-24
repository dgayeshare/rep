package org.creationpartenaire.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ONBOARD_COM_BY_PARTNER")
public class OnboardComByPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COM_BY_PARTNER")
    private Long idComByPartner;

    @ManyToOne
    @JoinColumn(name = "ID_PARTNER", nullable = false)
    private PartnerOnboarded partner;

    @Column(name = "VAL_DEB", nullable = false)
    private BigDecimal valDeb;

    @Column(name = "VAL_FIN", nullable = false)
    private BigDecimal valFin;

    @Column(name = "COMMISSION_ABB", nullable = false)
    private BigDecimal commissionAbb;

    @Column(name = "COMMISSION_CONF", nullable = false)
    private BigDecimal commissionConf;
}