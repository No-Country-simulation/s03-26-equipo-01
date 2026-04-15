package com.cms.persistence.sql;

import com.cms.model.user.User;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EditorSQLDAO extends JpaRepository<Editor, Long> {

    List<User> findAllByCreatedBy(Admin createdBy);

    @Query("""
        SELECT COUNT(t) > 0
        FROM Testimonial t
        WHERE t.editor = :editor AND t.id = :testimonialId
    """)
    Boolean containTestimonial(@Param("testimonialId") Long id, @Param("editor") Editor editor);
}
