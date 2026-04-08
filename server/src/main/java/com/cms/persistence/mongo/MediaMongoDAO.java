package com.cms.persistence.mongo;

import com.cms.model.testimonial.Media;
import com.cms.persistence.mongo.entity.MediaMongo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MediaMongoDAO extends MongoRepository<MediaMongo, String> {

    Optional<MediaMongo> findByImagePublicId(String imagePublicId);

    Optional<MediaMongo> findByVideoId(String videoId);
}