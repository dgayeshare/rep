package org.reporting.entity;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pel_paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PelPaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_paiement")
    private LocalDateTime datePaiement;

    @Column(name = "montant_total")
    private BigDecimal montantTotal;

    @Column(name = "mode_paiement_id")
    private Integer modePaiementId;

    @Column(name = "nom_payeur")
    private String nomPayeur;

    @Column(name = "prenom_payeur")
    private String prenomPayeur;

    @Column(name = "type_piece_identite_payeur")
    private String typePieceIdentitePayeur;

    @Column(name = "numero_piece_identite_payeur")
    private String numeroPieceIdentitePayeur;

    @Column(name = "utilisateur_saisie")
    private String utilisateurSaisie;

    @Column(name = "agence_saisie")
    private Integer agenceSaisie;

    @Column(name = "encadrant_id")
    private Integer encadrantId;

    @Column(name = "statut")
    private String statut;

    @Column(name = "date_annulation")
    private LocalDateTime dateAnnulation;

    @Column(name = "utilisateur_annulation")
    private String utilisateurAnnulation;

    @Column(name = "motif_annulation")
    private String motifAnnulation;

    @Column(name = "est_reimprimer")
    private String estReimprimer;

    @Column(name = "dateoper")
    private LocalDate dateOper;

    @Column(name = "codburpo")
    private BigDecimal codBurPo;

    @Column(name = "numordof")
    private BigDecimal numOrdOf;

    // Cette relation génère automatiquement getPaiementPelerins()
    @OneToMany(mappedBy = "paiement", fetch = FetchType.LAZY)
    private List<PelPaiementPelerin> paiementPelerins = new ArrayList<>();

    // Cette relation génère automatiquement getModePaiement()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mode_paiement_id", insertable = false, updatable = false)
    private PelModePaiement modePaiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paiement_id", insertable = false, updatable = false)
    private PelPaiementCheque paiementCheque;

    // Cette relation génère automatiquement getEncadrant()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encadrant_id", insertable = false, updatable = false)
    private PelEncadrant encadrant;

    public PelPaiementCheque getPaiementCheque() {
        return paiementCheque;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }

    public Integer getModePaiementId() {
        return modePaiementId;
    }

    public void setModePaiementId(Integer modePaiementId) {
        this.modePaiementId = modePaiementId;
    }

    public String getNomPayeur() {
        return nomPayeur;
    }

    public void setNomPayeur(String nomPayeur) {
        this.nomPayeur = nomPayeur;
    }

    public String getPrenomPayeur() {
        return prenomPayeur;
    }

    public void setPrenomPayeur(String prenomPayeur) {
        this.prenomPayeur = prenomPayeur;
    }

    public String getTypePieceIdentitePayeur() {
        return typePieceIdentitePayeur;
    }

    public void setTypePieceIdentitePayeur(String typePieceIdentitePayeur) {
        this.typePieceIdentitePayeur = typePieceIdentitePayeur;
    }

    public String getNumeroPieceIdentitePayeur() {
        return numeroPieceIdentitePayeur;
    }

    public void setNumeroPieceIdentitePayeur(String numeroPieceIdentitePayeur) {
        this.numeroPieceIdentitePayeur = numeroPieceIdentitePayeur;
    }

    public String getUtilisateurSaisie() {
        return utilisateurSaisie;
    }

    public void setUtilisateurSaisie(String utilisateurSaisie) {
        this.utilisateurSaisie = utilisateurSaisie;
    }

    public Integer getAgenceSaisie() {
        return agenceSaisie;
    }

    public void setAgenceSaisie(Integer agenceSaisie) {
        this.agenceSaisie = agenceSaisie;
    }

    public Integer getEncadrantId() {
        return encadrantId;
    }

    public void setEncadrantId(Integer encadrantId) {
        this.encadrantId = encadrantId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateAnnulation() {
        return dateAnnulation;
    }

    public void setDateAnnulation(LocalDateTime dateAnnulation) {
        this.dateAnnulation = dateAnnulation;
    }

    public String getUtilisateurAnnulation() {
        return utilisateurAnnulation;
    }

    public void setUtilisateurAnnulation(String utilisateurAnnulation) {
        this.utilisateurAnnulation = utilisateurAnnulation;
    }

    public String getMotifAnnulation() {
        return motifAnnulation;
    }

    public void setMotifAnnulation(String motifAnnulation) {
        this.motifAnnulation = motifAnnulation;
    }

    public String getEstReimprimer() {
        return estReimprimer;
    }

    public void setEstReimprimer(String estReimprimer) {
        this.estReimprimer = estReimprimer;
    }

    public LocalDate getDateOper() {
        return dateOper;
    }

    public void setDateOper(LocalDate dateOper) {
        this.dateOper = dateOper;
    }

    public BigDecimal getCodBurPo() {
        return codBurPo;
    }

    public void setCodBurPo(BigDecimal codBurPo) {
        this.codBurPo = codBurPo;
    }

    public BigDecimal getNumOrdOf() {
        return numOrdOf;
    }

    public void setNumOrdOf(BigDecimal numOrdOf) {
        this.numOrdOf = numOrdOf;
    }

    public List<PelPaiementPelerin> getPaiementPelerins() {
        return paiementPelerins;
    }

    public void setPaiementPelerins(List<PelPaiementPelerin> paiementPelerins) {
        this.paiementPelerins = paiementPelerins;
    }

    public PelModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(PelModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public PelEncadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(PelEncadrant encadrant) {
        this.encadrant = encadrant;
    }
}