package com.cms.services;

import com.cms.controller.dto.TagUpdateRequestDTO; // <-- Agregamos el import
import com.cms.model.Tag;
import java.util.List;

public interface TagService {
    Tag create(Tag tag);
    List<Tag> findAll();
    Tag findById(Long id);
    Tag update(Long id, TagUpdateRequestDTO updateTagDto); // <-- Actualizamos el nombre
    void deleteById(Long id);
}