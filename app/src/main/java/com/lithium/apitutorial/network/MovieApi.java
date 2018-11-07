package com.lithium.apitutorial.network;

import com.lithium.apitutorial.model.Movie;
import com.lithium.apitutorial.model.MovieSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    String BASE_URL = "http://www.omdbapi.com";

    @GET("/")
    Call<MovieSearchResult> searchMovies(@Query("apikey") String apikey, @Query("s") String searchTerm);

    @GET("/")
    Call<Movie> getMovie(@Query("apikey") String apikey, @Query("i") String id);

}
