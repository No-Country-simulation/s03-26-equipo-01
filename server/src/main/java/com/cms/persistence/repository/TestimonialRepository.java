package com.cms.persistence.repository;

import com.cms.model.testimonial.Testimonial;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestimonialRepository {

    Testimonial findById(Long id);
    Testimonial update(Testimonial model);

    Testimonial save(Testimonial model);

    List<Testimonial> findByAdminId(Long idAdmin);
}
