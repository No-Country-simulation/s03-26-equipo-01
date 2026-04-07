package com.cms.services;

import com.cms.model.testimonial.Testimonial;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestimonialService {
    Testimonial save(Testimonial model, Long id, MultipartFile image, String youtubeUrl);

    Testimonial findTestimonialById(Long id);

    List<Testimonial> findTestimonialByAdmin(Long idAdmin);

    Testimonial nextState(Long id, String role);
}
