package com.cms.persistence.repository;

import com.cms.model.testimonial.Testimonial;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestimonialRepository {

    Testimonial findById(Long id);

    List<Testimonial> findTestimonialByEmbeds(List<Long> embedIds);

    Testimonial save(Testimonial model);

    void deleteById(Long id);
}
