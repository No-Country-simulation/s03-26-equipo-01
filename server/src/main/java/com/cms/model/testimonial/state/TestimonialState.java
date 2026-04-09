package com.cms.model.testimonial.state;

import com.cms.model.testimonial.Testimonial;
import lombok.*;

@Data
@NoArgsConstructor
public abstract class TestimonialState {

    public abstract TestimonialState nextToEditor(Testimonial testimonial);

    public abstract TestimonialState nextToAdmin(Testimonial testimonial);

}
