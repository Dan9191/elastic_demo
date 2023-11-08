package com.example.elasticsearchlearningtorankjava.models.response;

import com.example.elasticsearchlearningtorankjava.models.mapper.Movie;
import lombok.Data;

@Data
public class ResponseMovie {

    private String title;
    private String overview;

    public ResponseMovie(Movie movie) {
        this.title = movie.getTitle();
        this.overview = movie.getOverview();
    }

    @Override
    public String toString() {
        return title + "\n" + "overview='" + overview + "\n";
    }
}
