package com.cms.persistence.sql;

import com.cms.model.user.impl.admin.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyDAOSQL extends JpaRepository<ApiKey, Integer> {
    Optional<ApiKey> findByPrefixAndActiveTrue(String prefix);
}
