package com.cms.model.user.impl;

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
}
