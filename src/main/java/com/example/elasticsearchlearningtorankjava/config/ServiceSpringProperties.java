package com.example.elasticsearchlearningtorankjava.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("demo.props")
@Data
public class ServiceSpringProperties {

    private String fileResourcePath;

    private String indexName;
}
