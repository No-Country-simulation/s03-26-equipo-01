package com.cms.model.testimonial;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.testimonial.state.TestimonialState;
import com.cms.model.testimonial.state.impl.DraftState;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"embed"})
public class Testimonial {

    private Long id;

    private String testimonial;

    private Embed embed;

    private int rating;

    private String email;

    private Media media;

    private StateTestimonial state;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    @Builder.Default
    private TestimonialState testimonialState = new DraftState();

    public void nextState() {
        this.testimonialState = testimonialState.next(this);
        this.state = StateTestimonial.fromState(this.testimonialState);
    }
}
