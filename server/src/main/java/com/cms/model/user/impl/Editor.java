package com.cms.model.user.impl;

import com.cms.exception.business.BusinessException;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.User;
import com.cms.model.user.impl.admin.Admin;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(exclude = {"createdBy"})
public class Editor extends User {

    private Admin createdBy;

    @Builder.Default
    private List<Testimonial> drafts = new ArrayList<>();

    public void addDrafts(Testimonial testimonial) {
        drafts.add(testimonial);
    }

    public void removeDraft(Testimonial testimonial) {
        drafts.remove(testimonial);
    }

    public void validateAdvance(Testimonial testimonial) {
        if(!isContains(testimonial) || !testimonial.hasCategory()) throw new BusinessException("El testimonio no pertenece al editor!");
    }

    public boolean isContains(Testimonial testimonial) {
        return drafts.contains(testimonial);
    }

    public Integer getNroTestimonials() {
        return drafts.size();
    }

    public void validateUpdateTestimonial(Boolean hasTestimonial) {
        if(!hasTestimonial) throw new BusinessException("No puede editar un testimonio que no le pertenece");
    }
}
