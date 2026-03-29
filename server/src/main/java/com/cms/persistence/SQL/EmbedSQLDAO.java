package com.cms.persistence.SQL;

import com.cms.model.embed.Embed;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmbedSQLDAO extends JpaRepository<Embed, Long> {

}
