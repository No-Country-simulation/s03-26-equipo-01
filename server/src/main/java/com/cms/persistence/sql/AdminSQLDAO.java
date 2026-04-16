package com.cms.persistence.sql;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.user.impl.admin.AdminResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AdminSQLDAO extends JpaRepository<Admin, Long> {
    @Query("SELECT a FROM Admin a WHERE a.id = :id AND a.enabled = true")
    Optional<Admin> findByIdAndEnabledTrue(@Param("id") Long id);

    @Query("""
        SELECT COUNT(t) > 0 
        FROM Admin a 
            JOIN a.tags t 
        WHERE a.id = :idAdmin 
            AND a.enabled = true 
            AND t.name = :name
    """)
    Boolean hasNameTagInListAdmin(String name, Long idAdmin);

    @Query(
            value = "SELECT e FROM Editor e WHERE e.createdBy.id = :idAdmin ORDER BY e.id",
            countQuery = "SELECT COUNT(e) FROM Editor e WHERE e.createdBy.id = :idAdmin"
    )
    Page<Editor> findEditorsByAdmin(@Param("idAdmin") Long idAdmin, Pageable pageable);
}