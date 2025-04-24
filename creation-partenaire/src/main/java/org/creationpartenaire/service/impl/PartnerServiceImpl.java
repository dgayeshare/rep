package org.creationpartenaire.service.impl;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.creationpartenaire.dto.ApiResponse;
import org.creationpartenaire.dto.CreatePartnerRequest;
import org.creationpartenaire.dto.PalierDto;
import org.creationpartenaire.entity.OnboardComByPartner;
import org.creationpartenaire.entity.OnboardPartnerDevise;
import org.creationpartenaire.entity.PartnerOnboarded;
import org.creationpartenaire.repository.OnboardComByPartnerRepository;
import org.creationpartenaire.repository.OnboardPartnerDeviseRepository;
import org.creationpartenaire.repository.PartnerOnboardedRepository;
import org.creationpartenaire.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.creationpartenaire.constants.ApiConstants.STATUS_ADDED;


@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerOnboardedRepository partnerRepository;

    @Autowired
    private OnboardPartnerDeviseRepository deviseRepository;

    @Autowired
    private OnboardComByPartnerRepository commissionRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private EmailService emailService;

    @Override
    @Transactional
    public ApiResponse createPartner(CreatePartnerRequest request) {
        try {
            // Vérifier si le partenaire existe déjà avec cet email
            if (partnerRepository.existsByEmailResp(request.getEmailPartenaire())) {
                return ApiResponse.error("409", "Un partenaire avec cet email existe déjà");
            }

            // Générer identifiant et mot de passe
            String identifiant = generatePartnerId();
            String password = generateRandomPassword();

            // Créer l'entité partenaire
            PartnerOnboarded partner = new PartnerOnboarded();
            partner.setNomPartner(request.getNomPartenaire());
            partner.setBicPartner(request.getBic());
            partner.setIdentifiantPartner(identifiant);
            partner.setPassword("changeme"); // passwordEncoder.encode(password)
            partner.setDateInsert(LocalDateTime.now());
            partner.setStatut(STATUS_ADDED);
            partner.setEmailResp(request.getEmailPartenaire());
            partner.setDateMajPassword(LocalDateTime.now());
            partner.setDatePartenariat(request.getDatePartenariat());
            partner.setComptePrincipal(request.getComptePrincipale());
            partner.setCompteCommission(request.getCompteCommission());
            partner.setTypeCommission(request.getTypeCommission());
            partner.setPeriodicitePrelev(request.getPeriodicite());
            partner.setTypePrelev(request.getTypePrelevement());
            partner.setValeurComFixeAbb(request.getValeurComFixeAbb());
            partner.setValeurComFixeConf(request.getValeurComFixeConf());
            partner.setComptePartVerifie(0);
            partner.setDateEnvoieMail(LocalDateTime.now());
            partner.setMotPasse(password); // Stocker le mot de passe non crypté pour l'envoyer par email
            partner.setTypeOperation("E"); // En attendant d'avoir une idee plus claire

            // Sauvegarder le partenaire
            System.out.println("le partner avant just de save: "+ partner);
            partner = partnerRepository.save(partner);

            // Sauvegarder les devises
            saveDevises(partner, request.getDevises());
            System.out.println("request palier: " + request.getPalliers());
            // Sauvegarder les paliers de commission si nécessaire
            if ((request.getTypePrelevement() == 3 || request.getTypePrelevement() == 4)
                    && request.getPalliers() != null && !request.getPalliers().isEmpty()) {
                saveCommissionPaliers(partner, request.getPalliers());
            }

            // Envoyer email avec identifiant et mot de passe
            sendWelcomeEmail(partner.getEmailResp(), identifiant, password);

            return ApiResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("500", "Erreur technique lors de la création du partenaire");
        }
    }

    private void saveDevises(PartnerOnboarded partner, List<String> devises) {
        for (String devise : devises) {
            OnboardPartnerDevise partnerDevise = new OnboardPartnerDevise();
            partnerDevise.setPartner(partner);
            partnerDevise.setCodeDevise(devise);
            deviseRepository.save(partnerDevise);
        }
    }

    private void saveCommissionPaliers(PartnerOnboarded partner, List<PalierDto> paliers) {
        for (PalierDto palier : paliers) {
          System.out.println("le palier avant just de save: "+ palier);
            OnboardComByPartner commission = new OnboardComByPartner();
            commission.setPartner(partner);
            commission.setValDeb(palier.getValeurDebut());
            commission.setValFin(palier.getValeurFin());
            commission.setCommissionAbb(palier.getCommissionAbb());
            commission.setCommissionConf(palier.getCommissionConf());
            commissionRepository.save(commission);
        }
    }

    private String generatePartnerId() {
        // Format: PARTABB + Année + Mois + Jour + HH + MM + SS + serial IdPartenaire
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);
        String random = String.format("%04d", new Random().nextInt(10000)); // 4-digit random number

        return "PARTABB" + timestamp + random;
    }

    private String generateRandomPassword() {
        // Générer un mot de passe aléatoire d'au moins 12 caractères
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Assure qu'il y a au moins une majuscule, une minuscule, un chiffre et un caractère spécial
        sb.append(chars.charAt(random.nextInt(26))); // Majuscule
        sb.append(chars.charAt(26 + random.nextInt(26))); // Minuscule
        sb.append(chars.charAt(52 + random.nextInt(10))); // Chiffre
        sb.append(chars.charAt(62 + random.nextInt(chars.length() - 62))); // Caractère spécial

        // Ajouter des caractères aléatoires pour atteindre la longueur désirée
        for (int i = 4; i < 12; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        // Mélanger le mot de passe
        List<Character> charList = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) {
            charList.add(c);
        }
        java.util.Collections.shuffle(charList);

        StringBuilder shuffled = new StringBuilder();
        for (char c : charList) {
            shuffled.append(c);
        }

        return shuffled.toString();
    }

    private void sendWelcomeEmail(String email, String identifiant, String password) {
        String subject = "Bienvenue - Vos informations de connexion";
        String body = String.format(
                "Bonjour,\n\n" +
                        "Votre compte a été créé avec succès.\n" +
                        "Voici vos identifiants de connexion :\n\n" +
                        "Identifiant : %s\n" +
                        "Mot de passe : %s\n\n" +
                        "Veuillez changer votre mot de passe lors de votre première connexion.\n\n" +
                        "Cordialement,\n" +
                        "L'équipe ABB",
                identifiant, password);

        //emailService.sendEmail(email, subject, body);
    }
}