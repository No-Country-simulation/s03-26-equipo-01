package com.cms.persistence.sql;

import com.cms.model.testimonial.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialSQLDAO extends JpaRepository<Testimonial, Long> {
}
