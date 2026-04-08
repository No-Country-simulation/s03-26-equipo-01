package com.cms.model.testimonial.state.impl;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.state.TestimonialState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


public class ApprovedState extends TestimonialState {

    @Override
    public TestimonialState next(Testimonial testimonial) {
        return null;
    }

    @Override
    public TestimonialState nextToAdmin(Testimonial testimonial) {
        return new PublishedState();
    }
}
