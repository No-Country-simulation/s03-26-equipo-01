package com.cms.services;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.impl.admin.Admin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestimonialService {
    Testimonial save(Testimonial model, Admin admin, MultipartFile image, String youtubeUrl, List<Long> idTags);

    Testimonial findTestimonialById(Long id);

    List<Testimonial> findTestimonialByAdmin(Long idAdmin);

    Testimonial advanceByEditor(Long id);

    Testimonial advanceByAdmin(Long idTestimonial);

    void update(Testimonial recovered);

}
