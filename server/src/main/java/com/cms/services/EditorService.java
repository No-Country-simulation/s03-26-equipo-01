package com.cms.services;

import com.cms.controller.dto.testimonial.TestimonialUpdateDTO;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EditorService {
    void asocTestimonial(Long idTestimonial, Long idEditor);

    Testimonial advanceByEditor(Long idTestimonial, Long idEditor);

    Page<Testimonial> getTestimonialsToBank(Long idEditor, int page, int size);

    Testimonial findTestimonialByIdAndEditor(Long id, Long editorId);

    List<Tag> findTagsByNameForEditor(String name, Long editorId, Long testimonialId);

    Testimonial updateTestimonial(TestimonialUpdateDTO model, Long editorId, Long categoryId);
}
