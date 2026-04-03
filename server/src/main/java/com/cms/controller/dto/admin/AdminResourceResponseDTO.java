package com.cms.controller.dto.admin;

import com.cms.model.user.impl.admin.AdminResource;

public record AdminResourceResponseDTO() {
    public static AdminResourceResponseDTO fromModel(AdminResource adminResource) {
        return new AdminResourceResponseDTO();
    }
}
