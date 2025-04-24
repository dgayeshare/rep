package org.creationpartenaire.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ONBOARD_PARTNER_DEVISE")
public class OnboardPartnerDevise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ONBOARD_PART_DEV")
    private Long idOnboardPartDev;

    @ManyToOne
    @JoinColumn(name = "ID_PARTNER", nullable = false)
    private PartnerOnboarded partner;

    @Column(name = "CODE_DEVISE", nullable = false)
    private String codeDevise;
}