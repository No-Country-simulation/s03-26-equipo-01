package com.cms.persistence.repository.mapper.impl;

import com.cms.model.testimonial.Tag;
import com.cms.persistence.elastic.entity.TagElastic;
import com.cms.persistence.repository.mapper.TagMapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapperImpl implements TagMapper {
    @Override
    public TagElastic toElastic(Tag tag) {
        return TagElastic.builder()
                .id(tag.getId().toString())
                .name(tag.getName())
                .build();
    }
}
