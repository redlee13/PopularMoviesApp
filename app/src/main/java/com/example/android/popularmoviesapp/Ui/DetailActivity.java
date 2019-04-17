package com.example.android.popularmoviesapp.Ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesapp.Adapters.ReviewAdapter;
import com.example.android.popularmoviesapp.Adapters.TrailerAdapter;
import com.example.android.popularmoviesapp.Constants;
import com.example.android.popularmoviesapp.Models.MovieModel;
import com.example.android.popularmoviesapp.Models.ReviewModel;
import com.example.android.popularmoviesapp.Models.TrailerModel;
import com.example.android.popularmoviesapp.R;
import com.example.android.popularmoviesapp.Util.JsonUtils;
import com.example.android.popularmoviesapp.Util.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    private MovieModel mMovieModel;

    @BindView(R.id.movie_title)
    TextView movieName;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.vote_average)
    TextView voteAverage;
    @BindView(R.id.trailers_recycler)
    RecyclerView mRecyclerViewGrid;
    @BindView(R.id.reviews_recycler)
    RecyclerView mRecyclerView;

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewGrid.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager1);

        new TrailerTask().execute(mMovieModel.getMovieId() + "");
        new ReviewTask().execute(mMovieModel.getMovieId()+ "");

        if (savedInstanceState == null) {

        }
    }

    private class TrailerTask extends AsyncTask<String, Void, ArrayList<TrailerModel>> {
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
            if (trailerList != null) {
                TrailerAdapter adapter = new TrailerAdapter(DetailActivity.this, trailerList);
                mRecyclerViewGrid.setAdapter(adapter);
            }
        }
    }

    private class ReviewTask extends AsyncTask<String, Void, ArrayList<ReviewModel>> {

        @Override
        protected ArrayList<ReviewModel> doInBackground(String... movieId) {
            try {
                URL url = NetworkUtils.buildReviewUrl(movieId[0]);
                String response = NetworkUtils.getResponseFromHttpUrl(url);
                return JsonUtils.getJsonReviewList(response);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<ReviewModel> reviewModels) {
            if (reviewModels != null){
                ReviewAdapter adapter = new ReviewAdapter(DetailActivity.this, reviewModels);
                mRecyclerView.setAdapter(adapter);
            }

        }
    }
}

