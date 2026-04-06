package com.cms.controller.dto.admin;

import com.cms.controller.dto.category.CategoryResponseSimpleDTO;
import com.cms.controller.dto.tag.TagResponseDto;
import com.cms.controller.dto.user.UserResponseSimpleDTO;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.admin.AdminResource;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

@Schema(name = "AdminResourceResponse", description = "Recursos necesarios para el panel del administrador")
public record AdminResourceResponseDTO(

        @Schema(description = "Lista de editores asociados al administrador")
        List<UserResponseSimpleDTO> users,

        @Schema(description = "Lista de categorías creadas por el administrador")
        List<CategoryResponseSimpleDTO> category,

        @Schema(description = "Lista de los posibles estados de un testimonio")
        List<String> states,

        @Schema(description = "Lista de tags creadas por el administrador")
        List<TagResponseDto> tags

) {
    public static AdminResourceResponseDTO fromModel(AdminResource adminResource) {
        return new AdminResourceResponseDTO(
                adminResource.getUsers().stream().map(UserResponseSimpleDTO::fromModel).toList(),
                adminResource.getCategories().stream().map(CategoryResponseSimpleDTO::fromModel).toList(),
                Arrays.stream(StateTestimonial.values())
                        .map(StateTestimonial::getLabel)
                        .toList(),
                adminResource.getTags().stream().map(TagResponseDto::fromEntity).toList()

        );
    }
}
