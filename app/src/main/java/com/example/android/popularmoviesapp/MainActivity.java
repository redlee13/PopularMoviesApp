package com.example.android.popularmoviesapp;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    RecyclerView movieRecyclerGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerGrid = findViewById(R.id.recycler_view);
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
        if (item.getItemId() == R.id.highest_rated){
            NetworkUtils.baseUrl = Constants.BASE_URL_TOP_RATED;
        } else if (item.getItemId() == R.id.most_popular){
            NetworkUtils.baseUrl = Constants.BASE_URL_POPULAR;
        } else if (item.getItemId() == R.id.next_page){
            NetworkUtils.displayPage++;
        } else if (item.getItemId() == R.id.previous_page && NetworkUtils.displayPage != 1){
            NetworkUtils.displayPage--;
        }
        Log.d(TAG, "onOptionsItemSelected: " + NetworkUtils.displayPage);
        new JsonTask().execute();
        return false;
    }

    private class JsonTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        private static final String TAG = "JsonTask";

        @Override
        protected ArrayList<Movie> doInBackground(String... params) {
            try {
                URL url = NetworkUtils.buildUrl();
                String response = NetworkUtils.getResponseFromHttpUrl(url);
                Log.d(TAG, "doInBackground: " + response);
                return JsonUtils.getJsonMovieList(response);


            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> json) {
            if (json != null){
                MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, json);
                movieRecyclerGrid.setAdapter(movieAdapter);
            }
        }
    }

}


