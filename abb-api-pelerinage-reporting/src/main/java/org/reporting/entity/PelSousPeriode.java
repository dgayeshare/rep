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
//@Table(name = "pel_sous_periode")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class PelSousPeriode {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "libelle")
//    private String libelle;
//
//    @Column(name = "periode_id")
//    private Integer periodeId;
//
//    @Column(name = "date_debut")
//    private LocalDate dateDebut;
//
//    @Column(name = "date_fin")
//    private LocalDate dateFin;
//
//    // Relations
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "periode_id", insertable = false, updatable = false)
//    private PelPeriodePelerinage periode;
//
//    @OneToMany(mappedBy = "sousPeriode", fetch = FetchType.LAZY)
//    private List<Pelerin> pelerins = new ArrayList<>();
//}
