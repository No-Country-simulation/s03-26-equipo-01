package com.cms.controller.dto.testimonial;

import com.cms.controller.annotations.ValidImageFile;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Schema(name = "TestimonialRequest", description = "Payload para crear un nuevo testimonio")
public record TestimonialRequestDTO(

        @Schema(
                description = "Contenido del testimonio",
                example = "Excelente servicio, muy recomendable.",
                maximum = "300",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @NotBlank
        @Size(max = 300, message = "Testimonial must not exceed 300 characters")
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
                description = "Puntuación del testimonio (1 a 10)",
                example = "5",
                minimum = "1",
                maximum = "10",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @Min(1)
        @Max(10)
        int rating,

        @Schema(
                description = "Nombre de la persona que hizo el testimonio",
                example = "Roberto mendez",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        String witness,

        @Schema(
                description = "Imagen del testimonio",
                type = "string",
                format = "binary",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        @ValidImageFile
        MultipartFile image,

        @Schema(
                description = "URL del video de YouTube",
                example = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        String youtubeUrl,

        @Schema(
                description = "Id de los tags que van a asociarse al testimonio",
                example = "[1, 2, 3]",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        List<Long> idTags

) {
        public Testimonial toModel() {
                return Testimonial.builder()
                        .testimonial(testimonial)
                        .witness(witness)
                        .email(email)
                        .rating(rating)
                        .state(StateTestimonial.DRAFT)
                        .createdAt(LocalDate.now())
                        .build();
        }
}