package com.cms.persistence.sql;

import com.cms.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserSQLDAO extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.enabled = true")
    Optional<User> findByEmailAndEnabledTrue(@Param("email") String email);

    @Query("FROM User u WHERE u.enabled = :enabled")
    List<User> findAllByEnabled(@Param("enabled") boolean enabled);

    @Query("FROM User u ORDER BY u.enabled DESC")
    Page<User> findAllOrderByEnabledDesc(Pageable pageable);
}
