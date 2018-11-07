package com.lithium.apitutorial.model;

import com.squareup.moshi.Json;

import java.util.List;

public class MovieSearchResult {

    @Json(name="Search")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
