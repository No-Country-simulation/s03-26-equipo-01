package com.cms.persistence.sql;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.model.testimonial.Testimonial;
import java.util.List;
import com.cms.model.testimonial.enums.StateTestimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TestimonialSQLDAO extends JpaRepository<Testimonial, Long> {
    @Query("""
            SELECT new com.cms.controller.dto.metrics.TagMetricDTO(
                tag.id,
                tag.name,
                COUNT(DISTINCT testimonial.id)
            )
            FROM Tag tag
            LEFT JOIN tag.testimonials testimonial ON testimonial.embed.admin.id = :adminId
            WHERE tag.active = true
              AND tag.creator.id = :adminId
            GROUP BY tag.id, tag.name
            ORDER BY tag.name ASC
            """)
    List<TagMetricDTO> findAllMetricsTags(@Param("adminId") Long adminId);

    @Query("""
            SELECT new com.cms.controller.dto.metrics.CategoryMetricDTO(
                category.id,
                category.name,
                category.slug,
                COUNT(testimonial.id)
            )
            FROM Category category
            LEFT JOIN Testimonial testimonial ON testimonial.category.id = category.id
            WHERE category.creator.id = :adminId
              AND category.deleted = false
            GROUP BY category.id, category.name, category.slug
            ORDER BY category.name ASC
            """)
    List<CategoryMetricDTO> findAllMetricsCategories(@Param("adminId") Long adminId);

    @Query("SELECT COUNT(t.id) FROM Testimonial t WHERE t.category.id = :categoryId")
    long countTestimonialsByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT t FROM Testimonial t WHERE t.embed.id IN :ids AND t.state != :state")
    List<Testimonial> findAllByEmbedIs(
            @Param("ids") List<Long> ids,
            @Param("state") StateTestimonial state);
}
