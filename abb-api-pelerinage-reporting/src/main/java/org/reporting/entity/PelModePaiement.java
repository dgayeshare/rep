package org.reporting.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pel_mode_paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PelModePaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "code")
    private String code;

    // Relations
    @OneToMany(mappedBy = "modePaiement", fetch = FetchType.LAZY)
    private List<PelPaiement> paiements = new ArrayList<>();

    // Relation Many-to-Many avec encadrants
    @ManyToMany(mappedBy = "modesPaiement", fetch = FetchType.LAZY)
    private List<PelEncadrant> encadrants = new ArrayList<>();
}