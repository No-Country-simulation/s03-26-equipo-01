package com.cms.persistence.repository.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.repository.MediaRepository;
import com.cms.persistence.sql.TestimonialSQLDAO;
import com.cms.persistence.repository.TestimonialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestimonialRepositoryImpl implements TestimonialRepository {

    private final TestimonialSQLDAO testimonialSQLDAO;
    private final MediaRepository mediaRepository;

    public TestimonialRepositoryImpl(TestimonialSQLDAO testimonialSQLDAO, MediaRepository mediaRepository) {
        this.testimonialSQLDAO = testimonialSQLDAO;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public Testimonial save(Testimonial model) {
        return testimonialSQLDAO.save(model);
    }

    @Override
    public List<Testimonial> findByAdminId(Long idAdmin) {
        List<Testimonial> testimonials = testimonialSQLDAO.findByAdminIdAndNotDraft(idAdmin, StateTestimonial.DRAFT );
        testimonials.forEach(this::resolveMedia);
        testimonials.forEach(this::resolveState);
        return testimonials;
    }

    @Override
    public Page<Testimonial> findAllTestimonial(PageRequest of, Admin admin, StateTestimonial state) {
        return testimonialSQLDAO.findTopByState(state, of, admin);
    }

    @Override
    public Testimonial findByIdAndEditor(Long id, Editor editor) {
        return testimonialSQLDAO.findByIdAndEditor(id, editor).orElseThrow(()-> new EntityNotFoundException(Testimonial.class.getName(), id));
    }


    @Override
    public List<Long> getTagsIdUsedInTestimonialAscoEditor(Editor editor, Long testimonialId) {
        Testimonial testimonial = testimonialSQLDAO.findById(testimonialId)
                .orElseThrow(() -> new EntityNotFoundException(Testimonial.class.getName(), testimonialId));

        return testimonialSQLDAO.getTagsIdUsedInTestimonialAscoEditor(editor, testimonialId);
    }

    @Override
    public Testimonial findById(Long id) {
        Testimonial testimonial = testimonialSQLDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Testimonial.class.getName(), id));
        resolveMedia(testimonial);
        resolveState(testimonial);
        return testimonial;
    }


    @Override
    public Testimonial update(Testimonial model) {
        return save(model);
    }

    private void resolveMedia(Testimonial testimonial) {
        String mediaId = testimonial.getMedia() != null ? testimonial.getMedia().getId() : null;

        if (mediaId != null) {
            testimonial.setMedia(mediaRepository.findById(mediaId));
        }
    }

    private void resolveState(Testimonial testimonial) {
        testimonial.setTestimonialState(testimonial.getState().toState());
    }

}