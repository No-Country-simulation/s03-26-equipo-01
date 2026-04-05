package com.cms.model;

import com.cms.model.testimonial.Testimonial;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.cms.model.user.impl.admin.Admin;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    private Long id;
    private String name;
    private String slug;

    @Builder.Default
    private boolean active = true;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private Set<Testimonial> testimonials = new LinkedHashSet<>();

    private Admin creator;

    public void updateTag(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public void clearTestimonials() {
        if (this.testimonials != null) {
            this.testimonials.clear();
        }
    }
}