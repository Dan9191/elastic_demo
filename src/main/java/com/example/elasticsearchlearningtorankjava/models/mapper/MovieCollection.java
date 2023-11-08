package com.example.elasticsearchlearningtorankjava.models.mapper;

import lombok.Data;

//	"belongs_to_collection": {
//            "poster_path": "/a61qhaM73Acotl98fAxj6ey7YzE.jpg",
//            "backdrop_path": "/Yt2ZxbJv2HM842B6FNMr59Vhyb.jpg",
//            "name": "Rambo Collection",
//            "id": 5039
//            },
@Data
public class MovieCollection {
    private String poster_path;
    private String backdrop_path;
    private String name;
    private int id;
}
