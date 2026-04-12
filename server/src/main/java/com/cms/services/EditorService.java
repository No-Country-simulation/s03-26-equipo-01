package com.cms.services;

import com.cms.model.testimonial.Testimonial;
import org.springframework.data.domain.Page;

public interface EditorService {
    void asocTestimonial(Long idTestimonial, Long idEditor);

    Testimonial advanceByEditor(Long idTestimonial, Long idEditor);

    Page<Testimonial> getTestimonialsToBank(Long idEditor, int page, int size);
}
