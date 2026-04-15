package com.cms.persistence.elastic;

import com.cms.persistence.elastic.entity.TagElastic;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.List;

public interface TagElasticDAO extends ElasticsearchRepository<TagElastic, String> {
    List<TagElastic> findAllByName(String name, String idAdmin);

    List<TagElastic> findAllByNameAndIdAdmin(String name, String idAdmin);

    @Query("""
    {
      "bool": {
        "must": [
          { "match": { "name": "?0" } },
          { "term": { "idAdmin": "?1" } }
        ],
        "must_not": [
          { "ids": { "values": ?2 } }
        ]
      }
    }
    """)
    List<TagElastic> findAllByNameAndIdAdminExcludeIdsString(String name, String string, List<String> idsString);
}
