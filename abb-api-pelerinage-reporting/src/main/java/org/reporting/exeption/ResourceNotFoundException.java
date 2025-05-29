package org.reporting.exeption;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceType;
    private final String resourceId;

    public ResourceNotFoundException(String resourceType, String resourceId) {
        super(String.format("%s avec l'identifiant '%s' non trouvé", resourceType, resourceId));
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }

    public ResourceNotFoundException(String resourceType, Long resourceId) {
        this(resourceType, String.valueOf(resourceId));
    }

    // Méthodes statiques pour créer facilement des exceptions courantes
    public static ResourceNotFoundException pelerin(String numeroInscription) {
        return new ResourceNotFoundException("Pèlerin", numeroInscription);
    }

    public static ResourceNotFoundException paiement(Long paiementId) {
        return new ResourceNotFoundException("Paiement", paiementId);
    }

    public static ResourceNotFoundException remise(String remiseId) {
        return new ResourceNotFoundException("Remise", remiseId);
    }

    public static ResourceNotFoundException agence(String codeAgence) {
        return new ResourceNotFoundException("Agence", codeAgence);
    }
}
