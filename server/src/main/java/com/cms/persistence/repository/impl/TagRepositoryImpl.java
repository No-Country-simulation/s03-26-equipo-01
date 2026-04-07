package com.cms.persistence.repository.impl;

import com.cms.model.testimonial.Tag;
import com.cms.persistence.elastic.TagElasticDAO;
import com.cms.persistence.elastic.entity.TagElastic;
import com.cms.persistence.repository.TagRepository;
import com.cms.persistence.repository.mapper.TagMapper;
import com.cms.persistence.sql.TagSQLDAO;
import org.springframework.stereotype.Repository;

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

    public Tag save(Tag tag) {
        Tag saved = tagSQLDAO.save(tag);

        TagElastic elastic = tagMapper.toElastic(saved);

        tagElasticDAO.save(elastic);

        return saved;
    }


}
