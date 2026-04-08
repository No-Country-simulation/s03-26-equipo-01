package com.cms.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.support.HttpHeaders;

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    @Value("${ELASTIC_URL}")
    private String elasticUri;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elasticUri.replace("http://", ""))
                // Este header es CRUCIAL para que ES 8 hable con clientes nuevos/viejos
                .withDefaultHeaders(new HttpHeaders() {{
                    add("Accept", "application/vnd.elasticsearch+json;compatible-with=8");
                    add("Content-Type", "application/vnd.elasticsearch+json;compatible-with=8");
                }})
                .build();
    }
}