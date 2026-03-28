package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.Tag;
import com.cms.persistence.sql.TagSQLDAO;
import com.cms.services.TagService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagSQLDAO tagSQLDAO;

    @Override
    public Tag create(Tag tag) {
        return tagSQLDAO.save(tag);
    }

    @Override
    public List<Tag> findAll() {
        return tagSQLDAO.findAll();
    }

    @Override
    public Tag findById(Long id) {
        return tagSQLDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Tag.class.getSimpleName(), id));
    }

    @Override
    public Tag update(Long id, Tag tag) {
        Tag tagToUpdate = findById(id);
        tagToUpdate.setName(tag.getName());
        return tagSQLDAO.save(tagToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Tag tag = findById(id);
        tagSQLDAO.delete(tag);
    }
}
