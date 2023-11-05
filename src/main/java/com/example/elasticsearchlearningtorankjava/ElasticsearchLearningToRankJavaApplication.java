package com.example.elasticsearchlearningtorankjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.elasticsearchlearningtorankjava.config")
public class ElasticsearchLearningToRankJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchLearningToRankJavaApplication.class, args);
    }

}
