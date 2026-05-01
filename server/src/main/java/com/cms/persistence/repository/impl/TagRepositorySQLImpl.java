package com.cms.persistence.repository.impl;

import com.cms.model.testimonial.Tag;
import com.cms.persistence.repository.TagRepository;
import com.cms.persistence.sql.TagSQLDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;


@Repository("tagRepositorySQLImpl")
@Primary
public class TagRepositorySQLImpl implements TagRepository {

    private final TagSQLDAO tagSQLDAO;

    public TagRepositorySQLImpl(TagSQLDAO tagSQLDAO) {
        this.tagSQLDAO = tagSQLDAO;
    }

    @Override
    public List<Tag> findAllByActiveTrueAndCreatorIdOrderByNameAsc(Long adminId) {
        return tagSQLDAO.findAllByActiveTrueAndCreatorIdOrderByNameAsc(adminId);
    }

    @Override
    public Optional<Tag> findByIdAndActiveTrue(Long id) {
        return tagSQLDAO.findByIdAndActiveTrue(id);
    }

    @Override
    public Tag saveAndFlush(Tag tag) {
        return tagSQLDAO.saveAndFlush(tag);
    }

    @Override
    public List<Tag> findTagsByName(String nameTag, Long idAdmin) {
        if (nameTag == null || nameTag.isEmpty() || nameTag.equals("")) {
            return findAllByActiveTrueAndCreatorIdOrderByNameAsc(idAdmin);
        }
        return tagSQLDAO.findByNameContainingIgnoreCaseAndCreatorIdAndActiveTrue(nameTag, idAdmin);
    }

    @Override
    public List<Tag> findTagsByNameExcludeIds(List<Long> ids, Long idAdmin, String name) {
        if (ids == null || ids.isEmpty()) {
            return findTagsByName(name, idAdmin);
        }
        return tagSQLDAO.findByNameContainingIgnoreCaseAndCreatorIdAndActiveTrue(name, idAdmin, ids);
    }
}
