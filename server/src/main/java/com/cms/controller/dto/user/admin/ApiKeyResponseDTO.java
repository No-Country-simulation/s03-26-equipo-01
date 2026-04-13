package com.cms.controller.dto.user.admin;

import com.cms.model.user.impl.admin.ApiKey;

public record ApiKeyResponseDTO(
        String apiKey
) {
    public static ApiKeyResponseDTO fromModel(ApiKey key) {
        return new ApiKeyResponseDTO(
                key.getKeyHash()
        );
    }

}