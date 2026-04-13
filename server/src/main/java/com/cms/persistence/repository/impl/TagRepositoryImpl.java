package com.cms.persistence.repository.impl;

import com.cms.model.testimonial.Tag;
import com.cms.persistence.elastic.TagElasticDAO;
import com.cms.persistence.elastic.entity.TagElastic;
import com.cms.persistence.repository.TagRepository;
import com.cms.persistence.repository.mapper.TagMapper;
import com.cms.persistence.sql.TagSQLDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private final TagMapper tagMapper;

    private final TagElasticDAO tagElasticDAO;

    private final TagSQLDAO tagSQLDAO;

    public TagRepositoryImpl(TagMapper tagMapper, TagElasticDAO tagElasticDAO, TagSQLDAO tagSQLDAO) {
        this.tagMapper = tagMapper;
        this.tagElasticDAO = tagElasticDAO;
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

    public Tag saveAndFlush(Tag tag) {
        Tag saved = tagSQLDAO.saveAndFlush(tag);

        TagElastic elastic = tagMapper.toElastic(saved);

        tagElasticDAO.save(elastic);

        return saved;
    }

    @Override
    public List<Tag> findTagsByName(String nameTag, Long idAdmin) {
        List<TagElastic> elastics = tagElasticDAO.findAllByNameAndIdAdmin(nameTag, idAdmin.toString());

        return elastics.stream().map(tagMapper::fromElastic).toList();
    }


}
