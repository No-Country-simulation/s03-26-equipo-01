package com.cms.model.testimonial.state.impl;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.state.TestimonialState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class PendingState extends TestimonialState {

    @Override
    public TestimonialState next(Testimonial testimonial) {
        return new ApprovedState();
    }
}
