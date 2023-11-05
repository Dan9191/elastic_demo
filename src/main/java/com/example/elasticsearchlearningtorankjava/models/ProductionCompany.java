package com.example.elasticsearchlearningtorankjava.models;

import lombok.Data;

//	"production_companies": [
//            {
//            "name": "TriStar Pictures",
//            "id": 559
//            },
//            {
//            "name": "Carolco Pictures",
//            "id": 14723
//            }
//            ],
@Data
public class ProductionCompany {
    private String name;
    private int id;
}
