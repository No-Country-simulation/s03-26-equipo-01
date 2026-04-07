package com.cms.services;

import com.cms.model.testimonial.Media;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    Media findById(String mediaId);

    Media save(MultipartFile image, String youtubeUrl);

    void deleteImage(String publicId);

    void deleteVideo(String videoId);
}
