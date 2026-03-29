package com.cms.persistence.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.User;
import com.cms.persistence.SQL.TestimonialSQLDAO;
import com.cms.persistence.TestimonialRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TestimonialRepositoryImpl implements TestimonialRepository {

    private final TestimonialSQLDAO testimonialSQLDAO;

    public TestimonialRepositoryImpl(TestimonialSQLDAO testimonialSQLDAO) {
        this.testimonialSQLDAO = testimonialSQLDAO;
    }


    @Override
    public Testimonial save(Testimonial model) {
        return testimonialSQLDAO.save(model);
    }

    @Override
    public Testimonial findById(Long id) {
        return testimonialSQLDAO.findById(id).orElseThrow(() -> new EntityNotFoundException(Testimonial.class.getName(), id));

    }
}
