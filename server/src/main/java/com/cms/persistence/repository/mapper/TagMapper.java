package com.cms.persistence.repository.mapper;

import com.cms.model.testimonial.Tag;
import com.cms.persistence.elastic.entity.TagElastic;

public interface TagMapper {
    TagElastic toElastic(Tag tag);

    Tag fromElastic(TagElastic tagElastic);
}
