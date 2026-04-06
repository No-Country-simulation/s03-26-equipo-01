package com.cms.services.impl;

import com.cms.model.testimonial.Media;
import com.cms.persistence.mongo.entity.MediaMongo;
import com.cms.persistence.repository.MediaRepository;
import com.cms.services.ImageService;
import com.cms.services.MediaService;
import com.cms.services.YoutubeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaServiceImpl implements MediaService {

    private final ImageService imageService;

    private final YoutubeService youtubeService;

    private final MediaRepository mediaRepository;

    public MediaServiceImpl(ImageService imageService, YoutubeService youtubeService, MediaRepository mediaRepository) {
        this.imageService = imageService;
        this.youtubeService = youtubeService;
        this.mediaRepository = mediaRepository;
    }


    public Media findById(String mediaId) {
        return mediaRepository.findById(mediaId);
    }

    @Override
    public Media save(MultipartFile image, String youtubeUrl) {
        Media imageMedia = (image != null && !image.isEmpty()) ? imageService.guardarImagen(image) : null;
        Media videoMedia = (youtubeUrl != null && !youtubeUrl.isBlank()) ? youtubeService.fromUrl(youtubeUrl) : null;

        return mediaRepository.save(imageMedia, videoMedia);
    }

    @Override
    public void deleteImage(String publicId) {
        imageService.deleteImage(publicId);
        mediaRepository.clearImageFields(publicId);
    }


}
