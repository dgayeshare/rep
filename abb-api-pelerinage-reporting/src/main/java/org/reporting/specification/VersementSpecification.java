//package org.reporting.specification;
//
//
//import org.springframework.data.jpa.domain.Specification;
//import jakarta.persistence.criteria.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class VersementSpecification {
//
//    /**
//     * Construction pour les versements par organisation
//     */
//    public static Specification<Paiement> buildVersementOrganisationSpecification(
//            LocalDate dateDebut,
//            LocalDate dateFin,
//            String typeReglement) {
//
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            // Critère sur la période
//            if (dateDebut != null && dateFin != null) {
//                LocalDateTime debut = dateDebut.atStartOfDay();
//                LocalDateTime fin = dateFin.atTime(23, 59, 59);
//                predicates.add(criteriaBuilder.between(root.get("datePaiement"), debut, fin));
//            }
//
//            // Critère sur le type de règlement
//            if (typeReglement != null && !typeReglement.trim().isEmpty()) {
//                if ("MASSE".equals(typeReglement)) {
//                    predicates.add(criteriaBuilder.equal(root.get("typePaiement"), "CONTINGENT"));
//                } else if ("UNITAIRE".equals(typeReglement)) {
//                    predicates.add(criteriaBuilder.in(root.get("typePaiement"))
//                            .value("UNITAIRE").value("MULTIPLE"));
//                }
//            }
//
//            // Jointures
//            root.fetch("organisme", JoinType.LEFT);
//            root.fetch("agence", JoinType.LEFT);
//
//            query.distinct(true);
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    /**
//     * Groupement par encadrant et organisme
//     */
//    public static Specification<Paiement> withGrouping() {
//        return (root, query, criteriaBuilder) -> {
//            // Groupement par encadrant et organisme
//            query.groupBy(
//                    root.get("encadrant"),
//                    root.get("organisme").get("nom"),
//                    root.get("typePaiement")
//            );
//
//            // Sélection des champs agrégés
//            query.multiselect(
//                    root.get("encadrant"),
//                    root.get("organisme").get("nom"),
//                    root.get("typePaiement"),
//                    criteriaBuilder.count(root.get("pelerin")),
//                    criteriaBuilder.sum(root.get("montantPaye"))
//            );
//
//            return criteriaBuilder.conjunction();
//        };
//    }
//}
