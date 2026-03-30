package com.cms.persistence;

import com.cms.model.testimonial.Testimonial;

public interface TestimonialRepository {
    Testimonial save(Testimonial model);

    Testimonial findById(Long id);
}
