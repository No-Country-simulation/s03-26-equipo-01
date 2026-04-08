package com.cms.model.testimonial.state;

import com.cms.model.testimonial.Testimonial;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
public abstract class TestimonialState {

    public abstract TestimonialState next(Testimonial testimonial);

    public abstract TestimonialState nextToAdmin(Testimonial testimonial);
}
