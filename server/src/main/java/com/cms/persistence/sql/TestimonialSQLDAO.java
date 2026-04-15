package com.cms.persistence.sql;

import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.model.testimonial.Testimonial;
import java.util.List;
import java.util.Optional;

import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            LEFT JOIN tag.testimonials testimonial ON testimonial.admin.id = :adminId
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

    @Query("SELECT t FROM Testimonial t WHERE t.state = :state AND t.admin = :admin AND t.editor IS NULL ORDER BY t.createdAt DESC")
    Page<Testimonial> findTopByState(@Param("state") StateTestimonial state, Pageable pageable, @Param("admin") Admin admin);

    @Query("SELECT t FROM Testimonial t WHERE t.admin.id = :idAdmin AND t.state != :state")
    List<Testimonial> findByAdminIdAndNotDraft(
            @Param("idAdmin") Long idAdmin,
            @Param("state") StateTestimonial state);

    Optional<Testimonial> findByIdAndEditor(Long id, Editor editor);
}
