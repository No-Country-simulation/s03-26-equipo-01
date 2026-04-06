package com.cms.persistence.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "media")
public class MediaMongo {

    @Id
    private String id;

    private String imageUrl;
    private String imagePublicId;

    private String videoUrl;
    private String videoId;
    private String videoTitle;
    private String thumbnailUrl;
    private String channelName;
}