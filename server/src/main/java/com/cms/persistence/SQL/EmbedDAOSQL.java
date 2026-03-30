package com.cms.model.embeds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface EmbedDAOSQL extends JpaRepository<Embeds, Long> {

}
