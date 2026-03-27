package com.cms.persistence.repository.sql;

import com.cms.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagSQLDAO extends JpaRepository<Tag, Long> {
}
