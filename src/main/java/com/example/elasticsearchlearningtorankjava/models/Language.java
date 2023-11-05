package com.example.elasticsearchlearningtorankjava.models;

import lombok.Data;

//	"spoken_languages": [
//            {
//            "iso_639_1": "en",
//            "name": "English"
//            },
//            {
//            "iso_639_1": "vi",
//            "name": "Tiếng Việt"
//            }
//            ],
@Data
public class Language {
    private String iso_639_1;
    private String name;
}
