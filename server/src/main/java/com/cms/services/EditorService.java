package com.cms.services;

import com.cms.model.testimonial.Testimonial;

public interface EditorService {
    void asocTestimonial(Long idTestimonial, Long idEditor);

    Testimonial advanceByEditor(Long idTestimonial, Long idEditor);
}
