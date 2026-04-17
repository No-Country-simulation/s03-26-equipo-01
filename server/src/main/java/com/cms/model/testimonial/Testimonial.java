package com.cms.model.testimonial;

import com.cms.exception.business.BusinessException;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.testimonial.state.TestimonialState;
import com.cms.model.testimonial.state.impl.ArchivedState;
import com.cms.model.testimonial.state.impl.DraftState;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"category", "tags", "testimonialState", "admin"})
public class Testimonial {

    private Long id;

    private String witness;

    private String testimonial;

    private int rating;

    private String email;

    private Media media;

    private Category category;

    private Admin admin;

    private Editor editor;

    @Builder.Default
    private List<Tag> tags = new ArrayList<>();

    @Builder.Default
    private StateTestimonial state = StateTestimonial.DRAFT;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    public void agregarTags(List<Tag> tags) {
        this.tags.addAll(tags);
    }
    private TestimonialState testimonialState;

    public void nextStateEditor() {
        this.testimonialState = testimonialState.nextToEditor(this);
        this.state = StateTestimonial.fromState(this.testimonialState);
    }

    public void nextStateAdmin() {
        this.testimonialState = testimonialState.nextToAdmin(this);
        this.state = StateTestimonial.fromState(this.testimonialState);
    }

    public void validateMedia() {
        if (media.isNextState()) throw new BusinessException("No se puede pasar de estado PENDING, si el testimonio tiene una imagen y video");
    }

    public void validateCategory() {
        if(category == null) throw new BusinessException("No se puede pasar de estado PENDING, si el testimonio no tiene una categoria");
    }

    public void archived() {
        this.testimonialState = new ArchivedState();
        this.state = StateTestimonial.fromState(this.testimonialState);
    }

    public boolean hasCategory() {
        return category != null;
    }
}
