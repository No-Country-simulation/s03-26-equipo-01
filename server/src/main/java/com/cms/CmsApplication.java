package com.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cms.persistence.sql")
// Escaneo de MongoDB
@EnableMongoRepositories(basePackages = "com.cms.persistence.mongo")
// Escaneo de Elasticsearch - ESTA ES LA QUE FALTA O ESTÁ MAL
@EnableElasticsearchRepositories(basePackages = "com.cms.persistence.elastic")
public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

}
