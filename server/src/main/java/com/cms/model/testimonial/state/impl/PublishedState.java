package com.cms.model.testimonial.state.impl;

import com.cms.exception.business.BusinessException;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.state.TestimonialState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


public class PublishedState extends TestimonialState {

    @Override
    public TestimonialState nextToEditor(Testimonial testimonial) {
        throw new BusinessException("El editor no puede despublicar un testimonio en estado PUBLISHED");
    }

    @Override
    public TestimonialState nextToAdmin(Testimonial testimonial) {
        return new DraftState();
    }
}
