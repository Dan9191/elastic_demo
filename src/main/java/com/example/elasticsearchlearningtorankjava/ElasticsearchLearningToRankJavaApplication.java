package com.example.elasticsearchlearningtorankjava;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.example.elasticsearchlearningtorankjava.models.Movie;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ElasticsearchLearningToRankJavaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ElasticsearchLearningToRankJavaApplication.class, args);

		Resource jsonResource = new ClassPathResource("tmdb.json");
		File jsonFile = jsonResource.getFile();

		ObjectMapper objectMapper = new ObjectMapper();
		JavaType jsonNodeType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Movie.class);
		Map<String, Movie> movies = objectMapper.readValue(jsonFile, jsonNodeType);


		RestClient restClient = RestClient
				.builder(HttpHost.create("http://localhost:9200"))
				.build();
		ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
		ElasticsearchClient client = new ElasticsearchClient(transport);

		client.indices().delete(c -> c
				.index("tmdb")
		);

		client.indices().create(c -> c
				.index("tmdb")
		);

		movies.keySet().forEach(id ->
		{
			IndexResponse response;
			try {
				response = client.index(i -> i
						.index("tmdb")
						.id(id)
						.document(movies.get(id))
				);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

}
