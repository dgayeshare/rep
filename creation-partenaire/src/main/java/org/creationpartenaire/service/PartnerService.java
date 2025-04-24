package org.creationpartenaire.service;


import org.creationpartenaire.dto.ApiResponse;
import org.creationpartenaire.dto.CreatePartnerRequest;

public interface PartnerService {
    ApiResponse createPartner(CreatePartnerRequest request);
}