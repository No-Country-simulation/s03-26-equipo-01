package com.cms.persistence.repository;

import com.cms.model.testimonial.Testimonial;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface TestimonialRepository {

    Testimonial findById(Long id);

    List<Testimonial> findTestimonialByEmbeds(List<Long> embedIds);

    Testimonial update(Testimonial model);

    Testimonial save(Testimonial model);


    Optional<Testimonial> findTestimonialByIdAndAdminId(Long id, Long idAdmin);

    void deleteById(Long id);


}
