package com.cms.persistence.sql;

import com.cms.model.Category;
import java.util.List;
import java.util.Optional;

import com.cms.model.user.impl.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorySQLDAO extends JpaRepository<Category, Long> {

    List<Category> findAllByDeletedFalse();

    Optional<Category> findByIdAndDeletedFalse(Long id);

    @Query("FROM Category c WHERE c.creator = :admin AND c.deleted = false")
    List<Category> findAllByCreator(@Param("admin") Admin admin);
}
