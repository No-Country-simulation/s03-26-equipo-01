package com.cms.controller.dto.testimonial;

import com.cms.controller.dto.category.CategoryResponseDto;
import com.cms.controller.dto.category.CategoryResponseSimpleDTO;
import com.cms.controller.dto.tag.TagResponseDto;
import com.cms.controller.dto.testimonial.media.MediaResponseDTO;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record TestimonilasToEditorDTO(
        @Schema(description = "ID del testimonio", example = "1")
        Long id,

        @Schema(description = "Contenido del testimonio", example = "Excelente servicio, muy recomendable.")
        String testimonial,

        @Schema(description = "Estado del testimonio", example = "Pendiente")
        StateTestimonial  stateTestimonial,

        @Schema(description = "Media del testimonio")
        MediaResponseDTO media,

        @Schema(description = "Categoria asociada al testimonio")
        CategoryResponseSimpleDTO category
) {
    public static TestimonilasToEditorDTO fromModel(Testimonial testimonial){
        CategoryResponseSimpleDTO category = testimonial.getCategory() == null ? null : CategoryResponseSimpleDTO.fromModel(testimonial.getCategory());

        return new TestimonilasToEditorDTO(
                testimonial.getId(),
                testimonial.getTestimonial(),
                testimonial.getState(),
                MediaResponseDTO.fromModel(testimonial.getMedia()),
                category
        );
    }
}