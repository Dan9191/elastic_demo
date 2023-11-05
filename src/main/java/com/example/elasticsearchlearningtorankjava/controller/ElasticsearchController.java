package com.example.elasticsearchlearningtorankjava.controller;

import com.example.elasticsearchlearningtorankjava.service.ElasticsearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elastic")
@AllArgsConstructor
@Slf4j
public class ElasticsearchController {

    private final ElasticsearchService elasticsearchService;

    @GetMapping("/create_index")
    public ResponseEntity createIndex() {
        try {
            long startTime = System.currentTimeMillis();
            elasticsearchService.createIndex();
            String response = String.format("Индекс создан и наполнен за %s мс", System.currentTimeMillis() - startTime);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
