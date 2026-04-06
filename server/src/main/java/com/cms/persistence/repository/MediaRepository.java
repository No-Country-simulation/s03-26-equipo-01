package com.cms.persistence.repository;

import com.cms.model.testimonial.Media;
import org.springframework.web.multipart.MultipartFile;

public interface MediaRepository {

    Media save(MultipartFile image, String youtubeUrl);

    Media findById(String id);
}
