package com.cms.persistence.repository.sql;

import com.cms.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSQLDAO extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);
}
