package com.cms.persistence.sql;

import com.cms.model.user.User;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EditorSQLDAO extends JpaRepository<Editor, Long> {

    List<User> findAllByCreatedBy(Admin createdBy);
}
