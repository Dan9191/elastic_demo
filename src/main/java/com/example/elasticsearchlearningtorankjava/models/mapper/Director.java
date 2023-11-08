package com.example.elasticsearchlearningtorankjava.models.mapper;

import lombok.Data;

//	"directors": [
//            {
//            "name": "George P. Cosmatos",
//            "credit_id": "52fe42efc3a36847f802e28d",
//            "job": "Director",
//            "department": "Directing",
//            "profile_path": "/6DIKPjnDvr7oHgTPHTXM5te45Qt.jpg",
//            "id": 16566
//            }
//            ]
/**
 * Director.
 */
@Data
public class Director {
    private String name;
    private String credit_id;
    private String job;
    private String department;
    private String profile_path;
    private int id;
}
