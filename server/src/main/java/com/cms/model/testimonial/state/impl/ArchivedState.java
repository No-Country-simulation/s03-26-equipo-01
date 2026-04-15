package com.cms.model.testimonial.state.impl;

import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.state.TestimonialState;

public class ArchivedState extends TestimonialState {
    @Override
    public TestimonialState nextToEditor(Testimonial testimonial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TestimonialState nextToAdmin(Testimonial testimonial) {
        return new PendingState();
    }
}
