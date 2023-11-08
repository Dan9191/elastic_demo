package com.example.elasticsearchlearningtorankjava.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
@AllArgsConstructor
public class ElasticIndexInitialization {

    @PostConstruct
    public void initIndex() {
        initIndexLifecyclePolicy();
        initIndexTemplate();
        createInitialIndex();
    }

    private void initIndexLifecyclePolicy() {
        System.out.println("initIndexLifecyclePolicy");
    }

    private void initIndexTemplate() {
        System.out.println("initIndexTemplate");
    }

    private void createInitialIndex() {
        System.out.println("createInitialIndex");
    }

}
