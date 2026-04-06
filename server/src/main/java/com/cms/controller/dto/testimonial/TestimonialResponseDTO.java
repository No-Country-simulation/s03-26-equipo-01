package com.cms.controller.dto.testimonial;

import com.cms.controller.dto.category.CategoryResponseSimpleDTO;
import com.cms.controller.dto.testimonial.media.MediaResponseDTO;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import io.swagger.v3.oas.annotations.media.Schema;


import java.time.LocalDate;

@Schema(name = "TestimonialResponse", description = "Respuesta con los datos de un testimonio")
public record TestimonialResponseDTO(

        @Schema(description = "ID del testimonio", example = "1")
        Long id,

        @Schema(description = "Contenido del testimonio", example = "Excelente servicio, muy recomendable.")
        String testimonial,

        @Schema(description = "ID del recurso embebido asociado", example = "1")
        long idEmbed,

        @Schema(description = "Puntuación del testimonio (1 a 10)", example = "5")
        int rating,

        @Schema(description = "Media del testimonio")
        MediaResponseDTO media,

        @Schema(description = "Correo electrónico del autor del testimonio", example = "usuario@email.com")
        String email,

        @Schema(description = "Estado del testimonio", example = "DRAFT")
        StateTestimonial state,

        @Schema(description = "Fecha de creación del testimonio", example = "2026-04-02")
        LocalDate createdAt,

        CategoryResponseSimpleDTO category
) {
    public static TestimonialResponseDTO fromModel(Testimonial testimonial) {
        return new TestimonialResponseDTO(
                testimonial.getId(),
                testimonial.getTestimonial(),
                testimonial.getEmbed().getId(),
                testimonial.getRating(),
                MediaResponseDTO.fromModel(testimonial.getMedia()),
                testimonial.getEmail(),
                testimonial.getState(),
                testimonial.getCreatedAt(),
                CategoryResponseSimpleDTO.fromModel(testimonial.getCategory())
        );
    }
}
