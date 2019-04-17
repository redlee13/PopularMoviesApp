package com.example.android.popularmoviesapp.Ui;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmoviesapp.Constants;
import com.example.android.popularmoviesapp.GridAutofitLayoutManager;
import com.example.android.popularmoviesapp.Util.JsonUtils;
import com.example.android.popularmoviesapp.Models.MovieModel;
import com.example.android.popularmoviesapp.Adapters.MovieAdapter;
import com.example.android.popularmoviesapp.Util.NetworkUtils;
import com.example.android.popularmoviesapp.R;

import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recycler_view) RecyclerView movieRecyclerGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        GridLayoutManager manager = new GridAutofitLayoutManager(this, 540);

        movieRecyclerGrid.setLayoutManager(manager);
        new JsonTask().execute();
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
        switch (item.getItemId()){
            case R.id.highest_rated:
                NetworkUtils.baseUrl = Constants.BASE_URL_TOP_RATED;
                break;
            case R.id.most_popular:
                NetworkUtils.baseUrl = Constants.BASE_URL_POPULAR;
                break;
            case R.id.next_page:
                NetworkUtils.displayPage++;
                break;
            case R.id.previous_page:
                NetworkUtils.displayPage--;
                break;
        }
        Log.d(TAG, "onOptionsItemSelected: " + NetworkUtils.displayPage);
        new JsonTask().execute();
        return false;
    }

    private class JsonTask extends AsyncTask<String, Void, ArrayList<MovieModel>> {
        private static final String TAG = "JsonTask";

        @Override
        protected ArrayList<MovieModel> doInBackground(String... params) {
            try {
                URL url = NetworkUtils.buildUrl();
                String response = NetworkUtils.getResponseFromHttpUrl(url);
                return JsonUtils.getJsonMovieList(response);

            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<MovieModel> json) {
            if (json != null){
                MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, json);
                movieRecyclerGrid.setAdapter(movieAdapter);
            }
        }
    }

}


