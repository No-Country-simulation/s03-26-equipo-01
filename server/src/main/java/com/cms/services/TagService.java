package com.cms.services;

import com.cms.controller.dto.tag.TagUpdateRequestDTO;
import com.cms.model.testimonial.Tag;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface TagService {
    Tag create(Tag tag, Long idAdmin);

    List<Tag> findAll();

    Tag findById(Long id);

    Tag update(Long id, TagUpdateRequestDTO updateTagDto);

    void deleteById(Long id);

    List<Tag> findTagsByName(String nameTag, Long aLong);
}