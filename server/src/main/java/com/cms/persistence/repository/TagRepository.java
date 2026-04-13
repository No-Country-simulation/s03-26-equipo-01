package com.cms.persistence.repository;

import com.cms.model.testimonial.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    List<Tag> findAllByActiveTrueAndCreatorIdOrderByNameAsc(Long adminId);

    Optional<Tag> findByIdAndActiveTrue(Long id);

    Tag saveAndFlush(Tag tag);

    List<Tag> findTagsByName(String nameTag, Long idAdmin);
}
