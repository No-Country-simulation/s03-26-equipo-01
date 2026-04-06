package com.cms.persistence.repository.impl;

import com.cms.model.testimonial.Media;
import com.cms.persistence.mongo.MediaMongoDAO;
import com.cms.persistence.mongo.entity.MediaMongo;
import com.cms.persistence.repository.MediaRepository;
import com.cms.persistence.repository.mapper.MediaMapper;
import com.cms.services.ImageService;
import com.cms.services.YoutubeService;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class MediaRepositoryImpl implements MediaRepository {

    private final MediaMongoDAO mediaMongoDAO;

    private final ImageService imageService;

    private final YoutubeService youtubeService;

    private final MediaMapper mediaMapper;

    public MediaRepositoryImpl(MediaMongoDAO mediaMongoDAO, ImageService imageService, YoutubeService youtubeService, MediaMapper mediaMapper) {
        this.mediaMongoDAO = mediaMongoDAO;
        this.imageService = imageService;
        this.youtubeService = youtubeService;
        this.mediaMapper = mediaMapper;
    }

    @Override
    public Media save(MultipartFile image, String youtubeUrl) {
        Media imageMedia = (image != null && !image.isEmpty()) ? imageService.guardarImagen(image) : null;
        Media videoMedia = (youtubeUrl != null && !youtubeUrl.isBlank()) ? youtubeService.fromUrl(youtubeUrl) : null;

        Media media = mediaMapper.fromSources(imageMedia, videoMedia);

        MediaMongo saved = mediaMongoDAO.save(mediaMapper.toMongo(media));

        media.setId(saved.getId());

        return media;
    }

    @Override
    public Media findById(String id) {
        return mediaMongoDAO.findById(id)
                .map(mediaMapper::fromMongo)
                .orElse(null);
    }


}