package com.cms.services.impl;

import com.cms.model.testimonial.Testimonial;
import com.cms.persistence.TestimonialRepository;
import com.cms.services.TestimonialService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    @Override
    public Testimonial save(Testimonial model) {
        return testimonialRepository.save(model);
    }
}
