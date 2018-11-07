package com.lithium.apitutorial.model;

import com.squareup.moshi.Json;

public class Movie {

    @Json(name="imdbID")
    private String id;

    @Json(name="Title")
    private String title;

    @Json(name="Year")
    private String year;

    @Json(name="Type")
    private String type;

    @Json(name="Poster")
    private String posterUrl;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
