package com.lithium.apitutorial.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.lithium.apitutorial.R;
import com.lithium.apitutorial.model.MovieSearchResult;
import com.lithium.apitutorial.network.HttpClient;
import com.lithium.apitutorial.network.NetworkCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MovieSearchActivity extends AppCompatActivity {

    private static final String apiKey = "658b38e1";

    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_with_recycler);
        ButterKnife.bind(this);

        movieAdapter = new MovieAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMovies(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    void searchMovies(String searchTerm) {
        HttpClient.movieApi()
                .searchMovies(apiKey, searchTerm)
                .enqueue(new NetworkCallback<MovieSearchResult>() {
                    @Override
                    protected void onSuccess(MovieSearchResult response) {
                        movieAdapter.clear();
                        movieAdapter.addAll(response.getMovies());
                    }

                    @Override
                    protected void onFailure(Throwable t) {
                        Timber.e(t);
                    }
                });
    }
}
