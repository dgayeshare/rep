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
//public class RemiseSpecification {
//
//    /**
//     * Construction dynamique pour les remises en instance
//     */
//    public static Specification<Remise> buildInstanceSpecification(
//            LocalDate dateRemise,
//            String codeAgence) {
//
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            // Date de remise <= date système
//            if (dateRemise != null) {
//                LocalDateTime dateMax = dateRemise.atTime(23, 59, 59);
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateRemise"), dateMax));
//            }
//
//            // Statut = "SAISIE" ou "NUMERISATION" non envoyé
//            Predicate statutSaisie = criteriaBuilder.equal(root.get("statut"), "SAISIE");
//            Predicate statutNumerisation = criteriaBuilder.and(
//                    criteriaBuilder.equal(root.get("statut"), "NUMERISATION"),
//                    criteriaBuilder.equal(root.get("envoiCompensation"), false)
//            );
//            predicates.add(criteriaBuilder.or(statutSaisie, statutNumerisation));
//
//            // Filtre par agence si spécifié
//            if (codeAgence != null && !codeAgence.trim().isEmpty()) {
//                Join<Object, Object> agenceJoin = root.join("agence", JoinType.LEFT);
//                predicates.add(criteriaBuilder.equal(agenceJoin.get("code"), codeAgence));
//            }
//
//            // Jointures pour optimisation
//            root.fetch("agence", JoinType.LEFT);
//            root.fetch("cheque", JoinType.LEFT);
//
//            query.distinct(true);
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    /**
//     * Construction pour le suivi des remises
//     */
//    public static Specification<Remise> buildSuiviSpecification(
//            LocalDate dateRemise,
//            String codeAgence,
//            String statutRemise) {
//
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            // Date de remise <= date système
//            if (dateRemise != null) {
//                LocalDateTime dateMax = dateRemise.atTime(23, 59, 59);
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateRemise"), dateMax));
//            }
//
//            // Filtre par statut si spécifié
//            if (statutRemise != null && !statutRemise.trim().isEmpty()) {
//                predicates.add(criteriaBuilder.equal(root.get("statut"), statutRemise));
//            }
//
//            // Filtre par agence
//            if (codeAgence != null && !codeAgence.trim().isEmpty()) {
//                Join<Object, Object> agenceJoin = root.join("agence", JoinType.LEFT);
//                predicates.add(criteriaBuilder.equal(agenceJoin.get("code"), codeAgence));
//            }
//
//            // Jointures
//            root.fetch("agence", JoinType.LEFT);
//            root.fetch("cheque", JoinType.LEFT);
//
//            query.distinct(true);
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    /**
//     * Recherche par numéro de chèque
//     */
//    public static Specification<Remise> byNumeroCheque(String numeroCheque) {
//        return (root, query, criteriaBuilder) -> {
//            if (numeroCheque == null || numeroCheque.trim().isEmpty()) {
//                return criteriaBuilder.conjunction();
//            }
//            Join<Object, Object> chequeJoin = root.join("cheque", JoinType.LEFT);
//            return criteriaBuilder.equal(chequeJoin.get("numero"), numeroCheque.trim());
//        };
//    }
//
//    /**
//     * Recherche par référence de remise
//     */
//    public static Specification<Remise> byReference(String reference) {
//        return (root, query, criteriaBuilder) -> {
//            if (reference == null || reference.trim().isEmpty()) {
//                return criteriaBuilder.conjunction();
//            }
//            return criteriaBuilder.equal(root.get("reference"), reference.trim());
//        };
//    }
//
//    /**
//     * Calcul du nombre de jours de retard
//     */
//    public static Specification<Remise> withDelayCalculation() {
//        return (root, query, criteriaBuilder) -> {
//            // Ajouter une expression pour calculer le nombre de jours de retard
//            Expression<Long> daysDiff = criteriaBuilder.function(
//                    "DATEDIFF",
//                    Long.class,
//                    criteriaBuilder.currentDate(),
//                    root.get("dateRemise")
//            );
//
//            // Cette spécification ne filtre pas, elle ajoute juste le calcul
//            return criteriaBuilder.conjunction();
//        };
//    }
//}
