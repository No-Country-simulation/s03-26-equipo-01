package com.cms.persistence.repository;

import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestimonialRepository {

    Testimonial findById(Long id);
    Testimonial update(Testimonial model);

    Testimonial save(Testimonial model);

    List<Testimonial> findByAdminId(Long idAdmin);

    Page<Testimonial> findAllTestimonial(PageRequest of, Admin admin, StateTestimonial state);

    Testimonial findByIdAndEditor(Long id, Editor editor);

    List<Long> getTagsIdUsedInTestimonialAscoEditor(Editor editor, Long testimonialId);

    Testimonial findTestimonialByIdAndAdmin(Long idTestimonial, Admin admin);

    Page<Testimonial> getDraftsByEditor(Editor editor, Pageable pageable);

    List<Testimonial> findAllTestimonialPublished(Admin admin, StateTestimonial stateTestimonial);
}
