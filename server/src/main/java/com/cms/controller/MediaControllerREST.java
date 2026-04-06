package com.cms.controller;

import com.cms.controller.annotations.EditorEndpoint;
import com.cms.services.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
@Tag(name = "Media", description = "Operaciones relacionadas con la gestión de imágenes y videos en el CMS")
public class MediaControllerREST {

    private final MediaService mediaService;

    public MediaControllerREST(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @DeleteMapping("/image/{publicId}")
    @EditorEndpoint
    @Operation(summary = "Eliminar una imagen", description = "Elimina la imagen de Cloudinary y limpia los campos de imagen del documento. Requiere privilegios de EDITOR.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Imagen eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Imagen no encontrada")
    })
    public ResponseEntity<Void> deleteImage(@PathVariable String publicId) {
        mediaService.deleteImage(publicId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/video/{videoId}")
    @EditorEndpoint
    @Operation(summary = "Eliminar un video", description = "Limpia los campos de video asociados al videoId indicado. Requiere privilegios de EDITOR.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Video eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Video no encontrado")
    })
    public ResponseEntity<Void> deleteVideo(@PathVariable String videoId) {
        mediaService.deleteVideo(videoId);
        return ResponseEntity.noContent().build();
    }
}