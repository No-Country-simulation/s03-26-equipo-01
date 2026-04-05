package com.cms.model.testimonial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media {


    private String url ;

    private String publicId ;

    private String videoUrl;
    private String videoId;
    private String videoTitle;
    private String thumbnailUrl;
    private String channelName;

    public void setImageData(Media imageMedia) {
        if (imageMedia == null) return;
        this.url = imageMedia.getUrl();
        this.publicId = imageMedia.getPublicId();
    }

    public void setVideoData(Media videoMedia) {
        if (videoMedia == null) return;
        this.videoId = videoMedia.getVideoId();
        this.videoUrl = videoMedia.getVideoUrl();
        this.videoTitle = videoMedia.getVideoTitle();
        this.thumbnailUrl = videoMedia.getThumbnailUrl();
        this.channelName = videoMedia.getChannelName();
    }
}
