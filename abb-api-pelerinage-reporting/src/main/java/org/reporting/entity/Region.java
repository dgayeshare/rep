//package org.reporting.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "region")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Region {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "coderegi")
//    private Integer id;
//
//    @Column(name = "liberegi")
//    private String libelle;
//
//    @Column(name = "coderegi")
//    private String code;
//
//    // Relations
//    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
//    private List<Province> provinces = new ArrayList<>();
//}