package com.cms.model.testimonial;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.enums.StateTestimonial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Testimonial {
    private Long id;
    private String testimonial;
    private Embed embed;
    private int rating;
    private String email;
    private Media image;
    private StateTestimonial state;
    @Builder.Default
    private LocalDate createdAt = LocalDate.now();
}
