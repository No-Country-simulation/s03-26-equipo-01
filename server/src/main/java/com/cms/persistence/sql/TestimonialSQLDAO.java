package com.cms.persistence.sql;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestimonialSQLDAO extends JpaRepository<Testimonial, Long> {
    @Query("SELECT t FROM Testimonial t WHERE t.embed.id IN :ids")
    List<Testimonial> findAllByEmbedIs(@Param("ids") List<Long> ids);
}
