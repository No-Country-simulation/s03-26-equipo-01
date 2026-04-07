package com.cms.model.testimonial.state.impl;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.state.TestimonialState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


public class PublishedState extends TestimonialState {

    @Override
    public TestimonialState next(Testimonial testimonial) {
        throw new UnsupportedOperationException("No se permite esta operacion");
    }
}
