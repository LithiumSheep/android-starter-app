package com.lithium.apitutorial.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lithium.apitutorial.R;
import com.lithium.apitutorial.model.Movie;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends AbsRecyclerAdapter<Movie> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = getItem(position);
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;

        movieViewHolder.bind(movie);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_poster)
        ImageView posterImage;

        @BindView(R.id.movie_title)
        TextView title;

        @BindView(R.id.movie_year)
        TextView year;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Movie movie) {
            Picasso.get()
                    .load(movie.getPosterUrl())
                    .into(posterImage);

            title.setText(movie.getTitle());
            year.setText(movie.getYear());
        }
    }
}
