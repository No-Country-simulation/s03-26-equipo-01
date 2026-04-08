package com.cms.services.impl;

import com.cms.services.ResetService;
import org.bson.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.metamodel.EntityType;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ResetServiceImpl implements ResetService {

    @PersistenceContext
    private EntityManager entityManager;

    private final MongoTemplate mongoTemplate;
    private final ElasticsearchOperations elasticsearchOperations;

    public ResetServiceImpl(MongoTemplate mongoTemplate, ElasticsearchOperations elasticsearchOperations) {
        this.mongoTemplate = mongoTemplate;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public void resetAll() {
        resetSQL();
        resetMongo();
        resetElastic();
    }

    private void resetSQL() {
        Set<String> tablasExistentes = obtenerTablasExistentes();

        if (tablasExistentes.isEmpty()) {
            return;
        }

        for (String tableName : tablasExistentes) {
            limpiarTabla(tableName);
        }
    }

    private void resetMongo() {
        mongoTemplate.getDb().listCollectionNames().forEach(name ->
                mongoTemplate.getDb().getCollection(name).deleteMany(new Document())
        );
    }

    private void resetElastic() {
        elasticsearchOperations.indexOps(IndexCoordinates.of("tag")).delete();
    }

    private Set<String> obtenerTablasExistentes() {
        Set<String> tablas = new HashSet<>();

        try {
            String query = """
                    SELECT table_name 
                    FROM information_schema.tables 
                    WHERE table_schema = 'public' 
                    AND table_type = 'BASE TABLE'
                    """;

            @SuppressWarnings("unchecked")
            List<String> resultados = entityManager.createNativeQuery(query).getResultList();
            tablas.addAll(resultados);

        } catch (Exception e) {
            Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
            for (EntityType<?> entityType : entityTypes) {
                tablas.add(getTableName(entityType));
            }
        }

        return tablas;
    }

    private void limpiarTabla(String tableName) {
        try {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName + " CASCADE")
                    .executeUpdate();
        } catch (Exception e) {
            entityManager.createNativeQuery("DELETE FROM " + tableName)
                    .executeUpdate();
        }
    }

    private String getTableName(EntityType<?> entityType) {
        Class<?> entityClass = entityType.getJavaType();
        jakarta.persistence.Table tableAnnotation = entityClass.getAnnotation(jakarta.persistence.Table.class);
        if (tableAnnotation != null && !tableAnnotation.name().isEmpty()) {
            return tableAnnotation.name();
        }
        return entityClass.getSimpleName();
    }
}