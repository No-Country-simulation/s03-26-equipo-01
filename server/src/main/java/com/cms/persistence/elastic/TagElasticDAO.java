package com.cms.persistence.elastic;

import com.cms.persistence.elastic.entity.TagElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TagElasticDAO extends ElasticsearchRepository<TagElastic, String> {
}
