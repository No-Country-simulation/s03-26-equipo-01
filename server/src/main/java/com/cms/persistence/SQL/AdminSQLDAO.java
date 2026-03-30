package com.cms.persistence.SQL;
import com.cms.model.user.impl.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AdminSQLDAO extends JpaRepository<Admin, Long> {
    @Query("SELECT a FROM Admin a WHERE a.id = :id AND a.enabled = true")
    Optional<Admin> findByIdAndEnabledTrue(@Param("id") Long id);
}