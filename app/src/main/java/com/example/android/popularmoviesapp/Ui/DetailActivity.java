package com.example.android.popularmoviesapp.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesapp.Constants;
import com.example.android.popularmoviesapp.MovieModel;
import com.example.android.popularmoviesapp.R;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    private MovieModel mMovieModel;
    @BindView(R.id.movie_title) TextView movieName;
    @BindView(R.id.release_date) TextView releaseDate;
    @BindView(R.id.poster) ImageView poster;
    @BindView(R.id.overview) TextView overview;
    @BindView(R.id.vote_average) TextView voteAverage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        mMovieModel = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);
        movieName.setText(mMovieModel.getTitle());
        releaseDate.setText(mMovieModel.getReleaseDate());
        Picasso.get().load(Constants.POSTER_BASE_URL + mMovieModel.getPosterPath()).into(poster);
        overview.setText(mMovieModel.getOverview());
        voteAverage.setText(mMovieModel.getVotes() + "/10");


    }
}
