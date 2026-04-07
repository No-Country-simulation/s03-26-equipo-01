package com.cms.model.testimonial;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.enums.StateTestimonial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    private StateTestimonial state;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    public void agregarTags(List<Tag> tags) {
        this.tags.addAll(tags);
    }
}
