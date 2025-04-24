package org.creationpartenaire.dto;


import java.math.BigDecimal;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PalierDto {

    @NotNull(message = "La valeur de début est obligatoire")
    @JsonProperty("val_deb")
    private BigDecimal valeurDebut;

    @NotNull(message = "La valeur de fin est obligatoire")
    @JsonProperty("val_fin")
    private BigDecimal valeurFin;

    @NotNull(message = "La commission ABB est obligatoire")
    @JsonProperty("commission_abb")
    private BigDecimal commissionAbb;

    @NotNull(message = "La commission confrère est obligatoire")
    @JsonProperty("commission_conf")
    private BigDecimal commissionConf;
}