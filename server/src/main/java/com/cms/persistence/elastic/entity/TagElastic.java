package com.cms.persistence.elastic.entity;

// DESHABILITADO: Usando entidad SQL Tag en su lugar
/*
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setting(settingPath = "/elastic/tag-settings.json")
@Mapping(mappingPath = "/elastic/tag-mappings.json")
@Document(indexName = "tag")
public class TagElastic {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "autocomplete_search")
    private String name;

    private String idAdmin;
}
*/