
package com.cms.persistence.sql;

import com.cms.model.embeds.Embed;
import com.cms.model.user.impl.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmbedSQLDAO extends JpaRepository<Embed, Long> {

    @Query("SELECT e.id FROM Embed e WHERE e.admin = :admin")
    List<Long> findAllByAdmin(Admin admin);
}
