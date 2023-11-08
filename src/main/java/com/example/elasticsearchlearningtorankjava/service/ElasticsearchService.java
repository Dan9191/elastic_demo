package com.example.elasticsearchlearningtorankjava.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.elasticsearchlearningtorankjava.config.ServiceSpringProperties;
import com.example.elasticsearchlearningtorankjava.models.mapper.Movie;
import com.example.elasticsearchlearningtorankjava.models.response.ResponseMovie;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        if (elasticClient.indices().exists(c -> c.index(serviceSpringProperties.getIndexName())).value()) {
            elasticClient.indices().delete(c -> c.index(serviceSpringProperties.getIndexName()));
        }

        elasticClient.indices().create(c -> c.index(serviceSpringProperties.getIndexName()));

        movies.keySet().forEach(id ->
        {
            try {
                elasticClient.index(i -> i
                        .index("tmdb")
                        .id(id)
                        .document(movies.get(id))
                );
            } catch (IOException e) {
                log.error("Failed index creation");
                throw new RuntimeException(e);
            }
        });

        log.info("Index created and populated");
    }

//    {
//        "query": {
//        "multi_match": {
//            "query": "basketball with cartoon aliens", //query from UI
//                    "fields": ["title^10","overview"]
//        }
//    },
//        "size": 10,
//            "from": 0,
//            "sort": []
//    }
    public List<ResponseMovie> findWithMultiMatch(String searchText) throws IOException {
        Query byTitleAndOverview = MatchQuery.of(m -> m
                .field("title")
                .field("overview")
                .query(FieldValue.of(searchText))
        )._toQuery();

       Query multiMatchByTitleAndOverview = MultiMatchQuery.of(m -> m
               .fields(new ArrayList<>(Arrays.asList("title^10", "overview")))
               .query(searchText)
       )._toQuery();

        SearchResponse<Movie> response =
                elasticClient.search(
                        s -> s.index(serviceSpringProperties.getIndexName())
                                .query(q -> q.bool(b -> b.must(multiMatchByTitleAndOverview))),
                        Movie.class
        );

        List<Hit<Movie>> hits = response.hits().hits();

        List<ResponseMovie> responseMovies = new ArrayList<>();
        for (Hit<Movie> hit : hits) {
            Movie movie = hit.source();
            assert movie != null;
            ResponseMovie responseMovie = new ResponseMovie(movie);
            responseMovies.add(responseMovie);
            log.info("Found movie " + movie.getTitle() + ", score " + hit.score());
        }
        return responseMovies;
    }
}
