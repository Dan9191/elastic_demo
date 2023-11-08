package com.example.elasticsearchlearningtorankjava.models.mapper;

import lombok.Data;

//	"cast": [
//            {
//            "name": "Sylvester Stallone",
//            "character": "John J. Rambo",
//            "order": 0,
//            "cast_id": 27,
//            "credit_id": "52fe42f0c3a36847f802e327",
//            "profile_path": "/gnmwOa46C2TP35N7ARSzboTdx2u.jpg",
//            "id": 16483
//            },
//   ]
/**
 * Cast.
 */
@Data
public class Actor {

    private String name;
    private String character;
    private int order;
    private int cast_id;
    private String credit_id;
    private String profile_path;
    private int id;
}
