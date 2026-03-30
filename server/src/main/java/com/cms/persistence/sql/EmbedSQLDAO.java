package com.cms.persistence.sql;

import com.cms.model.embeds.Embed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmbedSQLDAO extends JpaRepository<Embed, Long> {

}
