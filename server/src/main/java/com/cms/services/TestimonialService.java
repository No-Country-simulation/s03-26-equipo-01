package com.cms.services;

import com.cms.model.testimonial.Testimonial;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestimonialService {
    Testimonial save(Testimonial model, Long id, MultipartFile image, String youtubeUrl, Long idCategoria);

    Testimonial findTestimonialById(Long id);

    List<Testimonial> findTestimonialByAdmin(Long idAdmin);
}
