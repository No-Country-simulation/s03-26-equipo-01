package com.cms.persistence.impl;

import com.cms.model.testimonial.Testimonial;
import com.cms.persistence.SQL.TestimonialSQLDAO;
import com.cms.persistence.TestimonialRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TestimonialRepositoryImpl implements TestimonialRepository {

    private TestimonialSQLDAO testimonialSQLDAO;

    @Override
    public Testimonial save(Testimonial model) {
        return testimonialSQLDAO.save(model);
    }
}
