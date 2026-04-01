package com.cms.services;

import com.cms.model.Tag;
import java.util.List;

public interface TagService {
    Tag create(Tag tag);

    List<Tag> findAll();

    Tag findById(Long id);

    Tag update(Long id, Tag tagData);

    void deleteById(Long id);
}
