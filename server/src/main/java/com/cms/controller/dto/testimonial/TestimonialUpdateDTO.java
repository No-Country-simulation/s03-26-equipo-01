package com.cms.controller.dto.testimonial;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Media;
import java.util.List;

public record TestimonialUpdateDTO(
        Long id,
        Long categoryId,
        String testimonial,
        List<Long> tagIds,
        List<Long> removedTagIds,
        DisplayOptionsDTO displayOptions
) {

    public void updateTestimonial(Testimonial model) {
        model.setId(this.id);
        model.setTestimonial(this.testimonial);

        if (this.displayOptions != null && model.getMedia() != null) {
            Media media = model.getMedia();

            if (!this.displayOptions.showVideo()) {
                media.setVideoId(null);
                media.setVideoUrl(null);
                media.setVideoTitle(null);
                media.setThumbnailUrl(null);
                media.setChannelName(null);
            }

            if (!this.displayOptions.showImage()) {
                media.setUrl(null);
                media.setPublicId(null);
            }

            model.setMedia(media);
        }
    }

    public record DisplayOptionsDTO(
            boolean showVideo,
            boolean showImage
    ) {}
}