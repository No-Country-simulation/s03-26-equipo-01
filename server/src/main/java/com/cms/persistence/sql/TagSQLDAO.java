package com.cms.persistence.sql;

import com.cms.model.testimonial.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagSQLDAO extends JpaRepository<Tag, Long> {

    List<Tag> findAllByActiveTrueOrderByNameAsc();

    Optional<Tag> findByIdAndActiveTrue(Long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, Long id);
}
