package com.cms.model.testimonial.state;

import com.cms.model.testimonial.Testimonial;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class TestimonialState {

    public abstract TestimonialState next(Testimonial testimonial);
}
