package com.cms.model.testimonial.state.impl;

import com.cms.exception.business.BusinessException;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.state.TestimonialState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


public class PendingState extends TestimonialState {

    @Override
    public TestimonialState nextToEditor(Testimonial testimonial) {
        throw new BusinessException("El editor no puede aprobar un testimonio en estado PENDING");
    }

    @Override
    public TestimonialState nextToAdmin(Testimonial testimonial) {
        return new ApprovedState();
    }
}
