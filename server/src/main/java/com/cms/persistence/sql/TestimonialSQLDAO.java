package com.cms.persistence.sql;

import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.model.testimonial.Testimonial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TestimonialSQLDAO extends JpaRepository<Testimonial, Long> {
    @Query("SELECT t FROM Testimonial t WHERE t.embed.id IN :ids")
    List<Testimonial> findAllByEmbedIs(@Param("ids") List<Long> ids);

    @Query("""
            SELECT new com.cms.controller.dto.metrics.TagMetricDTO(
                tag.id,
                tag.name,
                tag.slug,
                COUNT(DISTINCT testimonial.id)
            )
            FROM Tag tag
            LEFT JOIN tag.testimonials testimonial
            WHERE tag.active = true
            GROUP BY tag.id, tag.name, tag.slug
            ORDER BY tag.name ASC
            """)
    List<TagMetricDTO> findAllMetricsTags();

    @Query("SELECT COUNT(t.id) FROM Testimonial t WHERE t.category.id = :categoryId")
    long countTestimonialsByCategoryId(@Param("categoryId") Long categoryId);
}
