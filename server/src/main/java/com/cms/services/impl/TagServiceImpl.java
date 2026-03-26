package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.Tag;
import com.cms.persistence.repository.TagRepository;
import com.cms.services.TagService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Tag.class.getSimpleName(), id));
    }

    @Override
    public Tag update(Long id, Tag tag) {
        Tag tagToUpdate = findById(id);
        tagToUpdate.setName(tag.getName());
        return tagRepository.save(tagToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Tag tag = findById(id);
        tagRepository.delete(tag);
    }
}
