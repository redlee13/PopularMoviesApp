package com.example.android.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mMovie = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);

        TextView movieName = findViewById(R.id.movie_title);
        movieName.setText(mMovie.getTitle());

        TextView releaseDate = findViewById(R.id.release_date);
        releaseDate.setText(mMovie.getReleaseDate());

        ImageView poster = findViewById(R.id.poster);
        Picasso.get().load(Constants.POSTER_BASE_URL + mMovie.getPosterPath()).into(poster);

        TextView overview = findViewById(R.id.overview);
        overview.setText(mMovie.getOverview());

        TextView voteAverage = findViewById(R.id.vote_average);
        voteAverage.setText(mMovie.getVotes() + "/10");


    }
}
