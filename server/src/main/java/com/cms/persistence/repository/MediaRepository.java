package com.cms.persistence.repository;

import com.cms.model.testimonial.Media;
import org.springframework.web.multipart.MultipartFile;

public interface MediaRepository {

    Media save(Media imageMedia , Media videoMedia);

    Media findById(String id);

    void clearImageFields(String publicId);
}
