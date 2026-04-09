package com.cms.model.testimonial;

import com.cms.exception.business.BusinessException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.testimonial.state.TestimonialState;
import com.cms.model.testimonial.state.impl.DraftState;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"embed", "category", "tags", "testimonialState"})
public class Testimonial {

    private Long id;

    private String witness;

    private String testimonial;

    private Embed embed;

    private int rating;

    private String email;

    private Media media;

    private Category category;

    @Builder.Default
    private List<Tag> tags = new ArrayList<>();

    private StateTestimonial state = StateTestimonial.DRAFT;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    public void agregarTags(List<Tag> tags) {
        this.tags.addAll(tags);
    }
    @Builder.Default
    private TestimonialState testimonialState = new DraftState();

    public void nextStateEditor() {
        this.testimonialState = testimonialState.nextToEditor(this);
        this.state = StateTestimonial.fromState(this.testimonialState);
    }

    public void nextStateAdmin() {
        this.testimonialState = testimonialState.nextToAdmin(this);
        this.state = StateTestimonial.fromState(this.testimonialState);
    }

    public void validateMedia() {
        if (media.hasImage() && media.hasVideo()) throw new BusinessException("No se puede pasar de estado PENDING, si el testimonio tiene una imagen y video");
    }
}
