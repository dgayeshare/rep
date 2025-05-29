//package org.reporting.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "pel_periode_pelerinage")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class PelPeriodePelerinage {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "libelle")
//    private String libelle;
//
//    @Column(name = "annee")
//    private Integer annee;
//
//    @Column(name = "date_debut")
//    private LocalDate dateDebut;
//
//    @Column(name = "date_fin")
//    private LocalDate dateFin;
//
//    @Column(name = "statut")
//    private String statut;
//
//    // Relations
//    @OneToMany(mappedBy = "periodePelerinage", fetch = FetchType.LAZY)
//    private List<Pelerin> pelerins = new ArrayList<>();
//
//    @OneToMany(mappedBy = "periode", fetch = FetchType.LAZY)
//    private List<PelSousPeriode> sousPeriodes = new ArrayList<>();
//}

