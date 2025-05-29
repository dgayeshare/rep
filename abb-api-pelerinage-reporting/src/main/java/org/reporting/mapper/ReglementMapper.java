package org.reporting.mapper;

import com.abb.pelerinage.reporting.dto.ReglementPelerinageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.reporting.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReglementMapper {

    @Mapping(source = "numeroPieceIdentitePayeur", target = "numeroPiPayeur")
    @Mapping(source = "nomPayeur", target = "nomPayeur")
    @Mapping(source = "prenomPayeur", target = "prenomPayeur")
    @Mapping(source = "datePaiement", target = "datePaiement")
    @Mapping(source = "montantTotal", target = "montantPaye")
    @Mapping(source = "agenceSaisie", target = "codeAgence")
    @Mapping(source = "numOrdOf", target = "numeroOrdre")

    // Champs avec jointures corrigées
    @Mapping(target = "province", expression = "java(getProvinceFromPaiement(paiement))")
    @Mapping(target = "region", expression = "java(getRegionFromPaiement(paiement))")
    @Mapping(target = "numeroCinPelerin", expression = "java(getCinFromPaiement(paiement))")
    @Mapping(target = "numeroInscriptionPelerin", expression = "java(getNumInscriptionFromPaiement(paiement))")
    @Mapping(target = "nomPelerin", expression = "java(getNomPelerinFromPaiement(paiement))")
    @Mapping(target = "prenomPelerin", expression = "java(getPrenomPelerinFromPaiement(paiement))")
    @Mapping(target = "numeroTelPelerin", expression = "java(getNumTelFromPaiement(paiement))")
    @Mapping(target = "montantSubvention", expression = "java(getMontantSubventionFromPaiement(paiement))")
    @Mapping(target = "montantGlobal", expression = "java(getMontantGlobalFromPaiement(paiement))")
    @Mapping(target = "typePaiement", expression = "java(getTypePaiement(paiement))")
    @Mapping(target = "modePaiement", expression = "java(getModePaiement(paiement))")
    @Mapping(target = "encadrant", expression = "java(getEncadrant(paiement))")
    @Mapping(target = "organisme", expression = "java(getOrganisme(paiement))")
    @Mapping(target = "codeOrganisateur", expression = "java(getCodeOrganisateur(paiement))")
    @Mapping(target = "nomAgence", expression = "java(getNomAgence(paiement))")

    // Champs constants
    @Mapping(target = "produit", constant = "PELERINAGE")
    @Mapping(target = "offre", constant = "STANDARD")
    @Mapping(target = "numeroRemise", constant = "")
    @Mapping(target = "statutRemise", constant = "")
    @Mapping(target = "numeroValeur", constant = "")

    ReglementPelerinageDto toDto(PelPaiement paiement);
    List<ReglementPelerinageDto> toDtoList(List<PelPaiement> paiements);


    default String getProvinceFromPaiement(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .map(Pelerin::getProvince)
                .map(PelProvince::getLibelle)
                .orElse("Non définie");
    }

    default String getRegionFromPaiement(PelPaiement paiement) {
//        return paiement.getPaiementPelerins().stream()
//                .findFirst()
//                .map(PelPaiementPelerin::getPelerin)
//                .map(Pelerin::getProvince)
//                .map(Province::getRegion)
//                .map(Region::getLibelle)
//                .orElse("Non définie");
        return null;
    }

    default String getCodeOrganisateur(PelPaiement paiement) {
        // Récupérer le code organisme si l'encadrant est une agence de voyage
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .filter(p -> {
                    // Vérifier si l'encadrant est une agence de voyage
                    return Optional.ofNullable(p.getEncadrant())
                            .map(PelEncadrant::getTypeEncadrant)
                            .map("AGENCE"::equals)
                            .orElse(false);
                })
                .map(Pelerin::getOrganisme)
                .map(org -> org.getId().toString())
                .orElse("");
    }

//    default String getNumeroDeRemise(PelPaiement paiement) {
//        var modePayment = getModePaiement(paiement);
//        if (Objects.equals(modePayment, "Chèque")) {
//            return paiement.getPaiementPelerins().stream()
//                    .findFirst()
//                    .map(PelPaiementPelerin::getPelerin)
//                    .map(pelerin::)
//                    .orElse(null);
//        }
//        return null;
//    }

    default String getModePaiement(PelPaiement paiement) {
        return Optional.ofNullable(paiement.getModePaiement())
                .map(PelModePaiement::getLibelle)
                .orElse("Non défini");
    }

    default String getCinFromPaiement(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .map(Pelerin::getCin)
                .orElse("");
    }

    default String getNumInscriptionFromPaiement(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .map(Pelerin::getNumInscription)
                .map(String::valueOf)
                .orElse("");
    }

    default String getNomPelerinFromPaiement(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .map(Pelerin::getNom)
                .orElse("");
    }

    default String getNomAgence(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getAgence)
                .map(AgenceVoyage::getNomAgence)
                .orElse("Non définie");
    }

    default String getPrenomPelerinFromPaiement(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .map(Pelerin::getPrenom)
                .orElse("");
    }

    default String getNumTelFromPaiement(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .map(Pelerin::getNumTel)
                .orElse("");
    }

    default double getMontantSubventionFromPaiement(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .map(Pelerin::getMontantSubvention)
                .orElse(BigDecimal.ZERO)
                .doubleValue();
    }

    default double getMontantGlobalFromPaiement(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .map(PelPaiementPelerin::getMontantGlobal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();
    }

    default ReglementPelerinageDto.TypePaiementEnum getTypePaiement(PelPaiement paiement) {
        int nombrePelerins = paiement.getPaiementPelerins().size();
        String typeValue = nombrePelerins > 1 ? "MULTIPLE" : "UNITAIRE";
        return ReglementPelerinageDto.TypePaiementEnum.fromValue(typeValue);
    }

    default String getEncadrant(PelPaiement paiement) {
        // D'abord essayer l'encadrant du paiement
        String encadrantPaiement = Optional.ofNullable(paiement.getEncadrant())
                .map(PelEncadrant::getLibelle)
                .orElse(null);

        if (encadrantPaiement != null) {
            return encadrantPaiement;
        }

        // Sinon prendre l'encadrant du premier pèlerin
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .map(Pelerin::getEncadrant)
                .map(PelEncadrant::getLibelle)
                .orElse("Non défini");
    }

    default String getOrganisme(PelPaiement paiement) {
        return paiement.getPaiementPelerins().stream()
                .findFirst()
                .map(PelPaiementPelerin::getPelerin)
                .map(Pelerin::getOrganisme)
                .map(PelOrganisme::getLibelle)
                .orElse("");
    }

    default OffsetDateTime map(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atOffset(ZoneOffset.UTC);
    }
}
