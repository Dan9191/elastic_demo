package com.example.elasticsearchlearningtorankjava.models;

import lombok.Data;

//"genres": [
//            {
//            "id": 28,
//            "name": "Action"
//            },
//            {
//            "id": 12,
//            "name": "Adventure"
//            }
//            ],
@Data
public class Genre {
    private int id;
    private String name;
}
