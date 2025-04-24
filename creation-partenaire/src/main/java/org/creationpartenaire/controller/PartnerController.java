package org.creationpartenaire.controller;

import org.creationpartenaire.dto.ApiResponse;
import org.creationpartenaire.dto.CreatePartnerRequest;
import org.creationpartenaire.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;

import static org.creationpartenaire.constants.ApiConstants.STATUS_SUCCESS;


@RestController
@RequestMapping("/ABB")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @PostMapping("/CreatePartner")
    public ResponseEntity<ApiResponse> createPartner(@Valid @RequestBody CreatePartnerRequest request) {
        System.out.println("createPartner request: " + request);

        ApiResponse response = partnerService.createPartner(request);

        if (STATUS_SUCCESS.equals(response.getStatut())) {
            return ResponseEntity.ok(response);
        } else {
            // On retourne un code erreur 400 pour les erreurs fonctionnelles
            return ResponseEntity.badRequest().body(response);
        }
    }
}