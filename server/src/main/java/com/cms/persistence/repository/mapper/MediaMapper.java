package com.cms.persistence.repository.mapper;

import com.cms.model.testimonial.Media;
import com.cms.persistence.mongo.entity.MediaMongo;

public interface MediaMapper {

    Media fromSources(Media imageMedia, Media videoMedia);

    Media fromMongo(MediaMongo mongo);

    MediaMongo toMongo(Media media);
}
