package org.reporting.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pel_encadrant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PelEncadrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "commission")
    private BigDecimal commission;

    @Column(name = "montant")
    private BigDecimal montant;

    @Column(name = "operation_pelerinage_id")
    private Integer operationPelerinageId;

    // Relations
    @OneToMany(mappedBy = "encadrant", fetch = FetchType.LAZY)
    private List<Pelerin> pelerins = new ArrayList<>();

    @OneToMany(mappedBy = "encadrant", fetch = FetchType.LAZY)
    private List<PelPaiement> paiements = new ArrayList<>();

    // Relation Many-to-Many avec organismes
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "encadrant_organisme",
            joinColumns = @JoinColumn(name = "encadrant_id"),
            inverseJoinColumns = @JoinColumn(name = "organisme_id")
    )
    private List<PelOrganisme> organismes = new ArrayList<>();

    // Relation Many-to-Many avec modes de paiement
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pel_encadrant_mode_paiement",
            joinColumns = @JoinColumn(name = "encadrant_id"),
            inverseJoinColumns = @JoinColumn(name = "mode_paiement_id")
    )
    private List<PelModePaiement> modesPaiement = new ArrayList<>();

    // Méthode utilitaire pour récupérer le libellé
    public String getLibelle() {
        return this.nom;
    }

    public Object getTypeEncadrant() {
        return null;
    }
}
