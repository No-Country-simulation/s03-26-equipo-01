package com.cms.model.testimonial.state.impl;

import com.cms.exception.business.BusinessException;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.testimonial.state.TestimonialState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public class DraftState extends TestimonialState {

    @Override
    public TestimonialState nextToEditor(Testimonial testimonial) {
        return new PendingState();
    }

    public TestimonialState nextToAdmin(Testimonial testimonial) {
        throw new BusinessException("El admin no puede aprobar un testimonio en estado DRAFT");
    }
}
