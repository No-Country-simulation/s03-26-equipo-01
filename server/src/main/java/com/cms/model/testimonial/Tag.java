package com.cms.model.testimonial;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.cms.model.user.impl.admin.Admin;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Tag {

    private Long id;
    private String name;

    @Builder.Default
    private boolean active = true;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private Set<Testimonial> testimonials = new LinkedHashSet<>();

    private Admin creator;

    public void updateTag(String name) {
        this.name = name;
    }

    public void clearTestimonials() {
        if (this.testimonials != null) {
            this.testimonials.clear();
        }
    }
}