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

    private final MediaMapper mediaMapper;

    public MediaRepositoryImpl(MediaMongoDAO mediaMongoDAO, MediaMapper mediaMapper) {
        this.mediaMongoDAO = mediaMongoDAO;
        this.mediaMapper = mediaMapper;
    }

    @Override
    public Media save(Media imageMedia , Media videoMedia) {
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

    @Override
    public void clearImageFields(String publicId) {
        mediaMongoDAO.findByImagePublicId(publicId).ifPresent(media -> {
            media.setImageUrl(null);
            media.setImagePublicId(null);
            mediaMongoDAO.save(media);
        });
    }

    @Override
    public void clearVideoField(String videoId) {
        mediaMongoDAO.findByVideoId(videoId).ifPresent( media -> {
            media.setVideoId(null);
            media.setVideoUrl(null);
            media.setVideoTitle(null);
            media.setThumbnailUrl(null);
            media.setChannelName(null);
            mediaMongoDAO.save(media);
        });
    }
    
    @Override
    public void clearVideoFieldByMediaId(String mediaId) {
        mediaMongoDAO.findById(mediaId).ifPresent(media -> {
            media.setVideoId(null);
            media.setVideoUrl(null);
            media.setVideoTitle(null);
            media.setThumbnailUrl(null);
            media.setChannelName(null);
            mediaMongoDAO.save(media);
        });
    }
    
    @Override
    public void clearImageFieldsByMediaId(String mediaId) {
        mediaMongoDAO.findById(mediaId).ifPresent(media -> {
            media.setImageUrl(null);
            media.setImagePublicId(null);
            mediaMongoDAO.save(media);
        });
    }


}