package org.reporting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organisme", schema = "ccp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PelOrganisme {

    @Id
    @Column(name = "id_organisme")
    private Integer id;

    @Column(name = "id_operation")
    private Integer idOperation;

    @Column(name = "organisme")
    private String libelle;

    @Column(name = "id_classe")
    private Integer idClasse;

    @Column(name = "montant")
    private BigDecimal montant;

    @Column(name = "subventionne")
    private Integer subventionne;

    // Relations
    @OneToMany(mappedBy = "organisme", fetch = FetchType.LAZY)
    private List<Pelerin> pelerins = new ArrayList<>();

    // Relation Many-to-Many avec encadrants
    @ManyToMany(mappedBy = "organismes", fetch = FetchType.LAZY)
    private List<PelEncadrant> encadrants = new ArrayList<>();
}