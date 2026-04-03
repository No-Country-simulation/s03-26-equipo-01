package com.cms.services;

import com.cms.controller.dto.TagUpdateRequestDTO;
import com.cms.model.Tag;
import java.util.List;

public interface TagService {
    Tag create(Tag tag);
    List<Tag> findAll();
    Tag findById(Long id);

    // ¡ESTA ES LA LÍNEA CLAVE! Tiene que decir TagUpdateRequestDTO
    Tag update(Long id, TagUpdateRequestDTO updateTagDto);

    void deleteById(Long id);
}