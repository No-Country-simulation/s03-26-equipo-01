package com.cms.model.embeds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmbedsRepository extends JpaRepository<Embeds, Long> {

    Optional<Embeds> findByEmbedToken(String embedToken);

    List<Embeds> findByAdminId(Long adminId);
}
