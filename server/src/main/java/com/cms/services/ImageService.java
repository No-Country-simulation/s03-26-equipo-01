package com.cms.services;

import com.cms.model.testimonial.Media;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.RowSet;

public interface ImageService {
    Media guardarImagen(MultipartFile image);

    void deleteImage(String publicId);
}
