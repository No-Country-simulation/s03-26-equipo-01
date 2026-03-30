package com.cms.persistence.sql;

import com.cms.model.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorySQLDAO extends JpaRepository<Category, Long> {

    List<Category> findAllByDeletedFalse();

    Optional<Category> findByIdAndDeletedFalse(Long id);

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, Long id);
}
