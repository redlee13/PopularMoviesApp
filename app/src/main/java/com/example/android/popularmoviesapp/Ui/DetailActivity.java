package com.example.android.popularmoviesapp.Ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesapp.Constants;
import com.example.android.popularmoviesapp.GridAutofitLayoutManager;
import com.example.android.popularmoviesapp.MovieModel;
import com.example.android.popularmoviesapp.R;
import com.example.android.popularmoviesapp.TrailerAdapter;
import com.example.android.popularmoviesapp.TrailerModel;
import com.example.android.popularmoviesapp.Util.JsonUtils;
import com.example.android.popularmoviesapp.Util.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    private MovieModel mMovieModel;
    private TrailerModel mTrailerModel;
    @BindView(R.id.movie_title) TextView movieName;
    @BindView(R.id.release_date) TextView releaseDate;
    @BindView(R.id.poster) ImageView poster;
    @BindView(R.id.overview) TextView overview;
    @BindView(R.id.vote_average) TextView voteAverage;
    @BindView(R.id.trailers_recycler) RecyclerView mRecyclerViewGrid;

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

        GridLayoutManager gridLayoutManager = new GridAutofitLayoutManager(this, 500);
        mRecyclerViewGrid.setLayoutManager(gridLayoutManager);

        new TrailerTask().execute(mMovieModel.getMovieId() + "");
    }

    private class TrailerTask extends AsyncTask<String, Void, ArrayList<TrailerModel>>{
        @Override
        protected ArrayList<TrailerModel> doInBackground(String... movieId) {
            try {
                URL url = NetworkUtils.buildTrailerUrl(movieId[0]);
                String response = NetworkUtils.getResponseFromHttpUrl(url);
                return JsonUtils.getJsonTrailerList(response);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<TrailerModel> trailerList) {
            if (trailerList != null){
                TrailerAdapter adapter = new TrailerAdapter(DetailActivity.this, trailerList);
                mRecyclerViewGrid.setAdapter(adapter);
            }
        }
    }
}
