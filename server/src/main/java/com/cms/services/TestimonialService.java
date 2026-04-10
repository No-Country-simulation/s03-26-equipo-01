package com.cms.services;

import com.cms.model.testimonial.Testimonial;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestimonialService {

    Testimonial save(Testimonial model, Long id, MultipartFile image, String youtubeUrl, Long idCategory, List<Long> idTags);
    Testimonial save(Testimonial model, Long id, MultipartFile image, String youtubeUrl, List<Long> idTags);

    Testimonial findTestimonialById(Long id);

    List<Testimonial> findTestimonialByAdmin(Long idAdmin);

    Testimonial deleteTestimonial(Long id);

    Testimonial advanceByEditor(Long id);

    Testimonial advanceByAdmin(Long idTestimonial);

    void update(Testimonial recovered);

}
