package com.cms.controller.dto.admin;

import com.cms.controller.dto.category.CategoryResponseSimpleDTO;
import com.cms.controller.dto.user.UserResponseSimpleDTO;
import com.cms.model.user.impl.admin.AdminResource;

import java.util.List;

public record AdminResourceResponseDTO(
        List<UserResponseSimpleDTO> users,
        List<CategoryResponseSimpleDTO> category
) {
    public static AdminResourceResponseDTO fromModel(AdminResource adminResource) {
        return new AdminResourceResponseDTO(
                adminResource.getUsers().stream().map(UserResponseSimpleDTO::fromModel).toList(),
                adminResource.getCategories().stream().map(CategoryResponseSimpleDTO::fromModel).toList()
        );
    }
}
