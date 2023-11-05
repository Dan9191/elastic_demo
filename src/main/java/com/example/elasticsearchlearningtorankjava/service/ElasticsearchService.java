package com.example.elasticsearchlearningtorankjava.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.example.elasticsearchlearningtorankjava.config.ServiceSpringProperties;
import com.example.elasticsearchlearningtorankjava.models.Movie;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
@Slf4j
public class ElasticsearchService {

    /**
     * Json mapper.
     */
    private final ObjectMapper objectMapper;

    /**
     * Elasticsearch client.
     */
    private final ElasticsearchClient elasticClient;

    /**
     * Application properties.
     */
    private final ServiceSpringProperties serviceSpringProperties;

    public void createIndex() throws IOException {
        Resource jsonResource = new ClassPathResource(serviceSpringProperties.getFileResourcePath());
        File jsonFile = jsonResource.getFile();

        JavaType jsonNodeType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Movie.class);
        Map<String, Movie> movies = objectMapper.readValue(jsonFile, jsonNodeType);

        elasticClient.indices().delete(c -> c
                .index(serviceSpringProperties.getIndexName())
        );

        elasticClient.indices().create(c -> c
                .index(serviceSpringProperties.getIndexName())
        );

        movies.keySet().forEach(id ->
        {
            try {
                elasticClient.index(i -> i
                        .index("tmdb")
                        .id(id)
                        .document(movies.get(id))
                );
                log.info("Index created and populated");
            } catch (IOException e) {
                log.error("Failed index creation");
                throw new RuntimeException(e);
            }
        });
    }
}
