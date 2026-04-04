package com.cms.persistence;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Testimonial;

import java.util.List;

public interface TestimonialRepository {
    Testimonial save(Testimonial model);

    Testimonial findById(Long id);

    List<Testimonial> findTestimonialByEmbeds(List<Long> embedIds);
}
