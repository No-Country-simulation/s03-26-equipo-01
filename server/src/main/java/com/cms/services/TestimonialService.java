package com.cms.services;

import com.cms.model.testimonial.Testimonial;

public interface TestimonialService {
    Testimonial save(Testimonial model, Long id);

    Testimonial findTestimonialById(Long id);
}
