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

    @Value("${ELASTIC_USER:#{null}}")
    private String elasticUser;

    @Value("${ELASTIC_PASSWORD:#{null}}")
    private String elasticPassword;

    @Override
    public ClientConfiguration clientConfiguration() {
        String host = elasticUri
                .replace("https://", "")
                .replace("http://", "");

        boolean isHttps = elasticUri.startsWith("https://");

        ClientConfiguration.MaybeSecureClientConfigurationBuilder builder = ClientConfiguration.builder()
                .connectedTo(isHttps ? host + ":443" : host);

        if (isHttps) {
            builder.usingSsl();
        }

        if (elasticUser != null && !elasticUser.isBlank()) {
            builder.withBasicAuth(elasticUser, elasticPassword);
        }

        return builder
                .withDefaultHeaders(new HttpHeaders() {{
                    add("Accept", "application/vnd.elasticsearch+json;compatible-with=8");
                    add("Content-Type", "application/vnd.elasticsearch+json;compatible-with=8");
                }})
                .build();
    }
}