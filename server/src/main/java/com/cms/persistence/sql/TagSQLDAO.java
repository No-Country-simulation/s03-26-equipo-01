package com.cms.persistence.sql;

import com.cms.model.testimonial.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagSQLDAO extends JpaRepository<Tag, Long> {

    @Query("""
            SELECT tag
            FROM Tag tag
            WHERE tag.active = true
              AND tag.creator.id = :adminId
            ORDER BY tag.name ASC
            """)
    List<Tag> findAllByActiveTrueAndCreatorIdOrderByNameAsc(@Param("adminId") Long adminId);

    Optional<Tag> findByIdAndActiveTrue(Long id);

    @Query("""
            SELECT tag
            FROM Tag tag
            WHERE tag.active = true
              AND tag.creator.id = :adminId
              AND LOWER(tag.name) LIKE LOWER(CONCAT('%', :name, '%'))
            ORDER BY tag.name ASC
            """)
    List<Tag> findByNameContainingIgnoreCaseAndCreatorIdAndActiveTrue(
            @Param("name") String name,
            @Param("adminId") Long adminId);

    @Query("""
            SELECT tag
            FROM Tag tag
            WHERE tag.active = true
              AND tag.creator.id = :adminId
              AND LOWER(tag.name) LIKE LOWER(CONCAT('%', :name, '%'))
              AND tag.id NOT IN :excludeIds
            ORDER BY tag.name ASC
            """)
    List<Tag> findByNameContainingIgnoreCaseAndCreatorIdAndActiveTrue(
            @Param("name") String name,
            @Param("adminId") Long adminId,
            @Param("excludeIds") List<Long> excludeIds);
}
