Comment ça fonctionne dans votre architecture :
1. Flux de Validation

Request → @Valid → Bean Validation → Controller → Service → Business Validation → Repository

2. Types de Validation

@Valid : Validation automatique des DTOs (annotations JSR-303)
@Validated : Validation au niveau classe/méthode
Validations métier : Dans les services (exceptions personnalisées)

3. Gestion des Erreurs

GlobalExceptionHandler intercepte TOUTES les exceptions
Réponses uniformes avec ErrorDto
Logs structurés pour le debugging

4. Avantages de cette approche

✅ Cohérence : Toutes les erreurs ont le même format
✅ Séparation : Validation technique vs métier
✅ Maintenabilité : Gestion centralisée des erreurs
✅ Debugging : Logs détaillés automatiques
✅ API : Réponses claires pour le front-end