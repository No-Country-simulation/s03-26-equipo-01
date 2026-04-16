package com.cms.services;

import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TestimonialService {
    Testimonial save(Testimonial model, Admin admin, MultipartFile image, String youtubeUrl, List<Long> idTags);

    Testimonial findTestimonialById(Long id);

    List<Testimonial> findTestimonialByAdmin(Long idAdmin);

    Testimonial advanceByEditor(Long id);

    Testimonial advanceByAdmin(Long idTestimonial);

    Testimonial update(Testimonial recovered);

    Page<Testimonial> findAllTestimonial(int pageNumber, int size, Admin admin, StateTestimonial state);


    Testimonial findByIdAndEditor(Long id, Editor editor);

    List<Tag> getTagsIdUsedInTestimonialAscoEditor(Editor editor, Long testimonialId, String name);
    Testimonial archiveTestimonial(Long idTestimonial, Admin admin);

    Page<Testimonial> getTestimonialsByEditor(Editor editor, int page, int size);

    List<Testimonial> findAllTestimonialPublished(Admin admin, StateTestimonial stateTestimonial);
}
