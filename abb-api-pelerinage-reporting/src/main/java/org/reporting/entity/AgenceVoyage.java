package org.reporting.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agence_voyage", schema = "ccp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgenceVoyage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agence", nullable = false)
    private Integer idAgence;

    @Column(name = "id_operation")
    private Integer idOperation;

    @Column(name = "nom_agence", length = 50)
    private String nomAgence;

    @Column(name = "code_agence", length = 10)
    private String codeAgence;

    @Column(name = "ville", length = 50)
    private String ville;

    @Column(name = "responsable", length = 50)
    private String responsable;

    @Column(name = "tel_fax", length = 50)
    private String telFax;

    @Column(name = "e_mail", length = 50)
    private String email;

    @Column(name = "banque", length = 50)
    private String banque;

    @Column(name = "rib", length = 24)
    private String rib;

    @Column(name = "classe")
    private Integer classe;

    // @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    // private List<PelPaiementPelerin> paiements;
}
