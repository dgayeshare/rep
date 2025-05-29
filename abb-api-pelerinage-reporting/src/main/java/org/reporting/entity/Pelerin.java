package org.reporting.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pelerin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelerin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cin")
    private String cin;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "num_inscription")
    private Long numInscription;

    @Column(name = "num_tel")
    private String numTel;

    @Column(name = "organisme_id")
    private Integer organismeId;

    @Column(name = "encadrant_id")
    private Integer encadrantId;

    @Column(name = "periode_pelerinage_id")
    private Integer periodePelerinageId;

    @Column(name = "sous_periode_id")
    private Integer sousPeriodeId;

    @Column(name = "statut")
    private String statut;

    @Column(name = "montant_subvention")
    private BigDecimal montantSubvention;

    @Column(name = "montant_global")
    private BigDecimal montantGlobal;

    @Column(name = "montant_a_verser")
    private BigDecimal montantAVerser;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @Column(name = "utilisateur_modification")
    private String utilisateurModification;

    @Column(name = "date_chargement")
    private LocalDate dateChargement;

    @Column(name = "utilisateur_chargement")
    private String utilisateurChargement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", insertable = false, updatable = false)
    private PelProvince province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organisme_id", insertable = false, updatable = false)
    private PelOrganisme organisme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encadrant_id", insertable = false, updatable = false)
    private PelEncadrant encadrant;

    // Relation inverse avec PelPaiementPelerin
    @OneToMany(mappedBy = "pelerin", fetch = FetchType.LAZY)
    private List<PelPaiementPelerin> paiementPelerins = new ArrayList<>();
}