package com.cms.controller.dto.testimonial;

import com.cms.controller.dto.tag.TagResponseDto;
import com.cms.controller.dto.testimonial.media.MediaResponseDTO;
import com.cms.model.testimonial.Testimonial;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record TestimonialResponseSimpleDTO(
        @Schema(description = "ID del testimonio", example = "1")
        Long id,

        @Schema(description = "Contenido del testimonio", example = "Excelente servicio, muy recomendable.")
        String testimonial,

        @Schema(description = "Puntuación del testimonio (1 a 10)", example = "5")
        int rating,

        @Schema(description = "Media del testimonio")
        MediaResponseDTO media,

        @Schema(description = "tags asociados al testimonio")
        List<TagResponseDto> tags
) {
    public static TestimonialResponseSimpleDTO fromModel(Testimonial testimonial){
        return new TestimonialResponseSimpleDTO(
                testimonial.getId(),
                testimonial.getTestimonial(),
                testimonial.getRating(),
                MediaResponseDTO.fromModel(testimonial.getMedia()),
                testimonial.getTags().stream().map(TagResponseDto::fromEntity).toList()
        );
    }
}
