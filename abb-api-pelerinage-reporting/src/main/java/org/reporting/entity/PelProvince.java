package org.reporting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pel_province", schema = "userrec")
public class PelProvince {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String libelle;

    @Column(name = "date_chargement")
    private LocalDate dateChargement;

    @Column(name = "agence_chargement", length = 255)
    private String agenceChargement;

    @Column(name = "utilisateur_chargement", length = 255)
    private String utilisateurChargement;

    @Column(name = "date_modification")
    private LocalDate dateModification;

    @Column(name = "utilisateur_modification", length = 255)
    private String utilisateurModification;

    @Column(name = "agence_modification", length = 255)
    private String agenceModification;

}
