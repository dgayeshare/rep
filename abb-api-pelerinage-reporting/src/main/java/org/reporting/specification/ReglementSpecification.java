package org.reporting.specification;


import org.reporting.entity.PelPaiement;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReglementSpecification {

    public static Specification<PelPaiement> withDateRange(LocalDate dateDebut, LocalDate dateFin) {
        return (root, query, criteriaBuilder) -> {
            if (dateDebut == null || dateFin == null) {
                return criteriaBuilder.conjunction();
            }

            LocalDateTime debut = dateDebut.atStartOfDay();
            LocalDateTime fin = dateFin.atTime(23, 59, 59);

            return criteriaBuilder.between(root.get("datePaiement"), debut, fin);
        };
    }

    public static Specification<PelPaiement> withStatut(String statut) {
        return (root, query, criteriaBuilder) -> {
            if (statut == null || statut.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            // Mapping des statuts du swagger vers BD
            String statutBD = mapStatutToBD(statut);
            return criteriaBuilder.equal(
                    criteriaBuilder.upper(root.get("statut")),
                    statutBD.toUpperCase()
            );
        };
    }

    // Méthode utilitaire pour mapper les statuts
    private static String mapStatutToBD(String statutSwagger) {
        switch (statutSwagger.toUpperCase()) {
            case "VALIDE":
                return "PAYE";  // Statut validé = payé en BD
            case "ANNULE":
                return "ANNULE"; // Statut annulé
            default:
                return statutSwagger;
        }
    }

    // Combinaison des critères
    public static Specification<PelPaiement> buildSpecification(LocalDate dateDebut,
                                                                LocalDate dateFin,
                                                                String statut) {
        return withDateRange(dateDebut, dateFin)
                .and(withStatut(statut));
    }

}