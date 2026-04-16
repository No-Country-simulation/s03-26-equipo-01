package com.cms.controller.dto.testimonial.media;

import com.cms.model.testimonial.Media;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "MediaResponse", description = "Datos multimedia asociada al testimonio")
public record MediaResponseDTO(

        @Schema(description = "URL pública de la imagen")
        String imageUrl,

        @Schema(description = "ID público del recurso en Cloudinary")
        String imagePublicId,

        @Schema(description = "URL del video embebido de YouTube")
        String videoUrl,

        @Schema(description = "ID del video de YouTube")
        String videoId,

        @Schema(description = "Título del video de YouTube")
        String videoTitle,

        @Schema(description = "URL del thumbnail del video")
        String thumbnailUrl,

        @Schema(description = "Nombre del canal de YouTube")
        String channelName
) {
    public static MediaResponseDTO fromModel(Media media) {
        return new MediaResponseDTO(
                media.getUrl(),
                media.getPublicId(),
                media.getVideoUrl(),
                media.getVideoId(),
                media.getVideoTitle(),
                media.getThumbnailUrl(),
                media.getChannelName()
        );
    }
}
