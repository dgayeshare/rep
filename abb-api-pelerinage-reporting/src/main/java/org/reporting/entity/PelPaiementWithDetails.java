package org.reporting.entity;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Classe pour récupérer toutes les données nécessaires
 * aux règlements de pèlerinage avec les jointures
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PelPaiementWithDetails {

    // Données du paiement principal
    private PelPaiement paiement;

    // Données du pèlerin
    private PelerinInfo pelerin;

    // Données de la province
    private ProvinceInfo province;

    // Mode de paiement
    private ModePaiementInfo modePaiement;

    // Agence
    private AgenceInfo agence;

    // Région
    private RegionInfo region;

    // Encadrant
    private EncadrantInfo encadrant;

    // Organisme
    private OrganismeInfo organisme;

    // Produit et offre
    private ProduitInfo produit;
    private OffreInfo offre;

    // Données spécifiques aux chèques
    private ChequeInfo cheque;

    // Informations calculées
    private Integer nombrePelerins;

    // Classes internes pour les informations liées
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PelerinInfo {
        private String cin;
        private Long numInscription;
        private String nom;
        private String prenom;
        private String numTel;
        private BigDecimal montantSubvention;
        private BigDecimal montantGlobal;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProvinceInfo {
        private String libelle;
        private String code;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ModePaiementInfo {
        private String code;
        private String libelle;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AgenceInfo {
        private String nom;
        private String code;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegionInfo {
        private String liberegi;
        private String coderegi;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EncadrantInfo {
        private String libelle;
        private String code;
        private String type; // MINISTERE_HABOUS ou AGENCE_VOYAGE
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrganismeInfo {
        private String libelle;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProduitInfo {
        private String nom;
        private String code;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OffreInfo {
        private String nom;
        private BigDecimal montant;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChequeInfo {
        private String numeroValeur;
        private String statutRemise;
        private String numeroRemise;
        private String agenceDomiciliataire;
    }
}