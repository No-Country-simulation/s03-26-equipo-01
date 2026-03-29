package com.cms.controller.dto.testimonial;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(name = "TestimonialRequest", description = "Payload para crear un nuevo testimonio")
public record TestimonialRequestDTO(

        @Schema(
                description = "Contenido del testimonio",
                example = "Excelente servicio, muy recomendable.",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @NotBlank
        String testimonial,

        @Schema(
                description = "Correo electrónico del autor del testimonio",
                example = "usuario@email.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @NotBlank
        String email,

        @Schema(
                description = "Puntuación del testimonio (0 a 5)",
                example = "5",
                minimum = "0",
                maximum = "5",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @Min(0)
        @Max(5)
        int rating,

        @Schema(
                description = "ID del recurso embebido asociado al testimonio",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        Long idEmbed

) {
    public Testimonial toModel() {
        return Testimonial.builder()
                .testimonial(testimonial)
                .email(email)
                .rating(rating)
                .state(StateTestimonial.DRAFT)
                .createdAt(LocalDate.now())
                .build();
    }
}