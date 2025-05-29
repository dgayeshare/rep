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
//public class AnnulationSpecification {
//
//    /**
//     * Construction dynamique pour les annulations
//     */
//    public static Specification<AnnulationPaiement> buildSpecification(
//            LocalDate dateDebut,
//            LocalDate dateFin,
//            String modeReglement,
//            String statut) {
//
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            // Critère sur la période
//            if (dateDebut != null && dateFin != null) {
//                LocalDateTime debut = dateDebut.atStartOfDay();
//                LocalDateTime fin = dateFin.atTime(23, 59, 59);
//                predicates.add(criteriaBuilder.between(root.get("dateDemande"), debut, fin));
//            }
//
//            // Critère sur le mode de règlement
//            if (modeReglement != null && !modeReglement.trim().isEmpty()) {
//                Join<Object, Object> paiementJoin = root.join("paiement", JoinType.LEFT);
//                predicates.add(criteriaBuilder.equal(paiementJoin.get("modeReglement"), modeReglement));
//            }
//
//            // Critère sur le statut d'annulation
//            if (statut != null && !statut.trim().isEmpty()) {
//                predicates.add(criteriaBuilder.equal(root.get("statut"), statut));
//            }
//
//            // Jointures pour optimisation
//            root.fetch("paiement", JoinType.LEFT);
//            root.fetch("paiement").fetch("pelerin", JoinType.LEFT);
//            root.fetch("paiement").fetch("agence", JoinType.LEFT);
//
//            query.distinct(true);
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    /**
//     * Recherche par numéro CIN du pèlerin
//     */
//    public static Specification<AnnulationPaiement> byNumeroCinPelerin(String numeroCin) {
//        return (root, query, criteriaBuilder) -> {
//            if (numeroCin == null || numeroCin.trim().isEmpty()) {
//                return criteriaBuilder.conjunction();
//            }
//            Join<Object, Object> paiementJoin = root.join("paiement", JoinType.LEFT);
//            Join<Object, Object> pelerinJoin = paiementJoin.join("pelerin", JoinType.LEFT);
//            return criteriaBuilder.equal(pelerinJoin.get("numeroCin"), numeroCin.trim());
//        };
//    }
//
//    /**
//     * Recherche par motif d'annulation
//     */
//    public static Specification<AnnulationPaiement> byMotif(String motif) {
//        return (root, query, criteriaBuilder) -> {
//            if (motif == null || motif.trim().isEmpty()) {
//                return criteriaBuilder.conjunction();
//            }
//            return criteriaBuilder.like(
//                    criteriaBuilder.lower(root.get("motif")),
//                    "%" + motif.toLowerCase() + "%"
//            );
//        };
//    }
//}
