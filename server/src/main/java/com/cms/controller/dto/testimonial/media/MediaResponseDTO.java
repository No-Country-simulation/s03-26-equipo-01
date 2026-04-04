package com.cms.controller.dto.testimonial.media;

import com.cms.model.testimonial.Media;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "MediaResponse", description = "Datos de la imagen asociada al testimonio")
public record MediaResponseDTO(

        @Schema(description = "URL pública de la imagen", example = "https://res.cloudinary.com/demo/image/upload/sample.jpg")
        String url,

        @Schema(description = "ID público del recurso en Cloudinary", example = "cms/testimonials/abc123")
        String public_id
) {
    public static MediaResponseDTO fromModel(Media image) {
        return new MediaResponseDTO(
                image.getUrl(),
                image.getPublicId()
        );
    }
}
