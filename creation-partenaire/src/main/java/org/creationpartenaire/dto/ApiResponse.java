package org.creationpartenaire.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.creationpartenaire.constants.ApiConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String statut;
    private String codeRetour;
    private String message;

    public static ApiResponse success() {
        return new ApiResponse(STATUS_SUCCESS, CODE_SUCCESS, null);
    }

    public static ApiResponse error(String code, String message) {
        return new ApiResponse(STATUS_FAILED, code, message);
    }
}