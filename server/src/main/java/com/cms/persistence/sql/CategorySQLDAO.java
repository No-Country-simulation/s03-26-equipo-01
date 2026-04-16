package com.cms.persistence.sql;

import com.cms.model.testimonial.Category;
import java.util.List;
import java.util.Optional;

import com.cms.model.user.impl.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorySQLDAO extends JpaRepository<Category, Long> {

    List<Category> findAllByDeletedFalse();

    List<Category> findAllByCreatorIdAndDeletedFalse(Long creatorId);

    Optional<Category> findByIdAndDeletedFalse(Long id);

    @Query("FROM Category c WHERE c.id = :categoryId AND c.creator.id = :adminId AND c.deleted = false")
    Optional<Category> findByIdAndAdminId(@Param("categoryId") Long categoryId, @Param("adminId") Long adminId);

    List<Category> findByNameContainingIgnoreCaseAndCreator(String name, Admin createdBy);
}
