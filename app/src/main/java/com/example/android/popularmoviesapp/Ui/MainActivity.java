package com.example.android.popularmoviesapp.Ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmoviesapp.Adapters.MovieAdapter;
import com.example.android.popularmoviesapp.Constants;
import com.example.android.popularmoviesapp.GridAutofitLayoutManager;
import com.example.android.popularmoviesapp.Models.MovieModel;
import com.example.android.popularmoviesapp.MovieViewModel;
import com.example.android.popularmoviesapp.R;
import com.example.android.popularmoviesapp.Util.JsonUtils;
import com.example.android.popularmoviesapp.Util.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recycler_view)
    RecyclerView movieRecyclerGrid;
    MovieViewModel viewModel;
    private ArrayList<MovieModel> movieList;
    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        GridLayoutManager manager = new GridAutofitLayoutManager(this, 540);
        movieRecyclerGrid.setLayoutManager(manager);

        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movieList);
        movieRecyclerGrid.setAdapter(movieAdapter);

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        listLoader();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        GridLayoutManager manager = new GridAutofitLayoutManager(this, 540);
        movieRecyclerGrid.setLayoutManager(manager);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.next_page:
                NetworkUtils.displayPage++;
                new JsonTask().execute();
                break;
            case R.id.previous_page:
                NetworkUtils.displayPage--;
                new JsonTask().execute();
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
        }
        Log.d(TAG, "onOptionsItemSelected: " + NetworkUtils.displayPage);
        return true;
    }

    private String getSort() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString(getString(R.string.sort_by_key), getString(R.string.pref_popular));
    }

    private void listLoader() {
        if (getSort().equals(getString(R.string.pref_fav))) {
            viewModel.getMovies(true, null).observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(@Nullable List<MovieModel> movieModels) {
                    movieList.clear();

                    if (movieModels != null) {
                        movieList.addAll(movieModels);
                        movieAdapter.notifyDataSetChanged();
                    }
                }
            });

        } else if (getSort().equals(getString(R.string.pref_popular))) {
            NetworkUtils.displayPage = 1;
            NetworkUtils.baseUrl = Constants.BASE_URL_POPULAR;
            viewModel.getMovies(false, NetworkUtils.buildUrl()).observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                    movieList.clear();
                    movieList.addAll(movieModels);
                    movieAdapter.notifyDataSetChanged();
                }
            });

        } else {
            NetworkUtils.displayPage = 1;
            NetworkUtils.baseUrl = Constants.BASE_URL_TOP_RATED;
            viewModel.getMovies(false, NetworkUtils.buildUrl()).observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                    movieList.clear();
                    movieList.addAll(movieModels);
                    movieAdapter.notifyDataSetChanged();
                }
            });
        }

    }

    private class JsonTask extends AsyncTask<String, Void, ArrayList<MovieModel>> {
        private static final String TAG = "JsonTask";

        @Override
        protected ArrayList<MovieModel> doInBackground(String... params) {
            try {
                URL url = NetworkUtils.buildUrl();
                String response = NetworkUtils.getResponseFromHttpUrl(url);
                return JsonUtils.getJsonMovieList(response);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<MovieModel> json) {
            if (json != null) {
                MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, json);
                movieRecyclerGrid.setAdapter(movieAdapter);
            }
        }
    }

}


