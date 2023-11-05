package com.example.elasticsearchlearningtorankjava.models;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Movie {

    private String poster_path;
    private List<Country> production_countries;
    private long revenue;
    private String overview;
    private boolean video;
    private long id;
    private List<Genre> genres;
    private String title;
    private String tagline;
    private long vote_count;
    private String homepage;
    private MovieCollection belongs_to_collection;
	private String original_language;
    private String status;
    private List<Language> spoken_languages;
    private String imdb_id;
    private boolean adult;
    private String backdrop_path;
    private List<ProductionCompany> production_companies;
    //todo LocalDate
    private String release_date;
    private double popularity;
    private String original_title;
    private long budget;
    private List<Actor> cast;
    private List<Director> directors;
    private double vote_average;
    private long runtime;

}
