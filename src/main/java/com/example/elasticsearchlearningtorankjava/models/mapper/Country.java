package com.example.elasticsearchlearningtorankjava.models.mapper;

import lombok.Data;

//	"production_countries": [
//            {
//            "iso_3166_1": "US",
//            "name": "United States of America"
//            }
//            ],
@Data
public class Country {
    private String iso_3166_1;
    private String name;
}
