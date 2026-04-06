package com.cms.controller;

import com.cms.services.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
public class MediaControllerREST {

    private final MediaService mediaService;

    public MediaControllerREST(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @DeleteMapping("/image/{publicId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String publicId) {
        mediaService.deleteImage(publicId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/video/{videoId}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String videoId) {
        mediaService.deleteVideo(videoId);

        return ResponseEntity.noContent().build();
    }
}
