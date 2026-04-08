package com.cms.persistence.repository.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.persistence.repository.MediaRepository;
import com.cms.persistence.sql.TestimonialSQLDAO;
import com.cms.persistence.repository.TestimonialRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
    public Testimonial findById(Long id) {
        Testimonial testimonial = testimonialSQLDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Testimonial.class.getName(), id));
        resolveMedia(testimonial);
        return testimonial;
    }

    @Override
    public List<Testimonial> findTestimonialByEmbeds(List<Long> embedIds) {
        List<Testimonial> testimonials = testimonialSQLDAO.findAllByEmbedIs(embedIds, StateTestimonial.DRAFT);
        testimonials.forEach(this::resolveMedia);
        return testimonials;
    }

    private void resolveMedia(Testimonial testimonial) {
        String mediaId = testimonial.getMedia() != null ? testimonial.getMedia().getId() : null;

        if (mediaId != null) {
            testimonial.setMedia(mediaRepository.findById(mediaId));
        }
    }

}
