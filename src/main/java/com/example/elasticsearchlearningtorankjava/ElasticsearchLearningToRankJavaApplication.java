package com.example.elasticsearchlearningtorankjava;

import com.example.elasticsearchlearningtorankjava.models.Movie;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
		movies.keySet().stream().findFirst().ifPresent(id -> System.out.println(movies.get(id).toString()));
	}

}
