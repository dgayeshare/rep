package org.creationpartenaire.entity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PARTNER_ONBOARDED")
public class PartnerOnboarded {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PARTNER")
    private Long idPartner;

    @Column(name = "NOM_PARTNER", nullable = false)
    private String nomPartner;

    @Column(name = "BIC_PARTNER")
    private String bicPartner;

    @Column(name = "IDENTIFIANT_PARTNER", nullable = false)
    private String identifiantPartner;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DATE_INSERT", nullable = false)
    private LocalDateTime dateInsert;

    @Column(name = "DATE_ACTIVATION")
    private LocalDateTime dateActivation;

    @Column(name = "DATE_DESACTIVATION")
    private LocalDateTime dateDesactivation;

    @Column(name = "STATUT", nullable = false)
    private String statut;

    @Column(name = "EMAIL_RESP", nullable = false)
    private String emailResp;

    @Column(name = "DATE_MAJ_PASSWORD", nullable = false)
    private LocalDateTime dateMajPassword;

    @Column(name = "DATE_EXPIRATION_PASSWORD")
    private LocalDateTime dateExpirationPassword;

    @Column(name = "DATE_PARTENARIAT", nullable = false)
    private LocalDate datePartenariat;

    @Column(name = "COMPTE_PRINCIPAL", nullable = false)
    private Long comptePrincipal;

    @Column(name = "COMPTE_COMMISSION", nullable = false)
    private Long compteCommission;

    @Column(name = "TYPE_COMMISSION", nullable = false)
    private Integer typeCommission;

    @Column(name = "PERIODICITE_PRELEV")
    private String periodicitePrelev;

    @Column(name = "TYPE_PRELEV")
    private Integer typePrelev;

    @Column(name = "VALEUR_COM_FIXE_ABB")
    private BigDecimal valeurComFixeAbb;

    @Column(name = "VALEUR_COM_FIXE_CONF")
    private BigDecimal valeurComFixeConf;

    @Column(name = "COMPTEPART_VERIFIE", nullable = false)
    private Integer comptePartVerifie = 0;

    @Column(name = "DATE_TOKEN")
    private LocalDateTime dateToken;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "DATE_ENVOIE_MAIL", nullable = false)
    private LocalDateTime dateEnvoieMail;

    @Column(name = "MOT_PASSE", nullable = false)
    private String motPasse;

    @Column(name = "TYPE_OPERATION", nullable = false)
    private String typeOperation;
}

