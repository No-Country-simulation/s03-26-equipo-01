package com.cms.persistence.repository.mapper.impl;

import com.cms.model.testimonial.Media;
import com.cms.persistence.mongo.entity.MediaMongo;
import com.cms.persistence.repository.mapper.MediaMapper;
import org.springframework.stereotype.Component;

@Component
public class MediaMapperImpl implements MediaMapper {

    public MediaMongo toMongo(Media media) {
        return MediaMongo.builder()
                .imageUrl(media.getUrl())
                .imagePublicId(media.getPublicId())
                .videoUrl(media.getVideoUrl())
                .videoId(media.getVideoId())
                .videoTitle(media.getVideoTitle())
                .thumbnailUrl(media.getThumbnailUrl())
                .channelName(media.getChannelName())
                .build();
    }

    @Override
    public Media fromSources(Media imageMedia, Media videoMedia) {
        Media media = Media.builder().build();
        media.setImageData(imageMedia);
        media.setVideoData(videoMedia);
        return media;
    }

    public Media fromMongo(MediaMongo mongo) {
        return Media.builder()
                .id(mongo.getId())
                .url(mongo.getImageUrl())
                .publicId(mongo.getImagePublicId())
                .videoUrl(mongo.getVideoUrl())
                .videoId(mongo.getVideoId())
                .videoTitle(mongo.getVideoTitle())
                .thumbnailUrl(mongo.getThumbnailUrl())
                .channelName(mongo.getChannelName())
                .build();
    }
}
