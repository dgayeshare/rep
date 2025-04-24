package org.creationpartenaire.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreatePartnerRequest {
    @JsonProperty("nom_partenaire")
    @NotBlank(message = "Le nom du partenaire est obligatoire")
    private String nomPartenaire;

    @JsonProperty("date_partenariat")
    @NotNull(message = "La date de partenariat est obligatoire")
    private LocalDate datePartenariat;

    private String bic;

    @JsonProperty("email_responsable")
    @NotBlank(message = "L'email du partenaire est obligatoire")
    @Email(message = "Format email invalide")
    private String emailPartenaire;

    @JsonProperty("compte_principal")
    @NotNull(message = "Le compte principal est obligatoire")
    private Long comptePrincipale;

    @JsonProperty("compte_commission")
    @NotNull(message = "Le compte commission est obligatoire")
    private Long compteCommission;

    @JsonProperty("type_commission")
    @NotNull(message = "Le type de commission est obligatoire")
    private Integer typeCommission;

    private String periodicite;

    @JsonProperty("type_prelevement")
    @NotNull(message = "Le type de prélèvement est obligatoire")
    private Integer typePrelevement;

    @JsonProperty("commission_abb")
    private BigDecimal valeurComFixeAbb;

    @JsonProperty("commission_confrere")
    private BigDecimal valeurComFixeConf;

    @JsonProperty("palliers")
    private List<PalierDto> palliers;

    @NotNull(message = "Au moins une devise doit être sélectionnée")
    private List<String> devises;
}