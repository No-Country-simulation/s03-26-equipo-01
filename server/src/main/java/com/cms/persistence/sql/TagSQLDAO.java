package com.cms.persistence.sql;

import com.cms.model.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagSQLDAO extends JpaRepository<Tag, Long> {

    List<Tag> findAllByDeletedFalse();

    Optional<Tag> findByIdAndDeletedFalse(Long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, Long id);
}
