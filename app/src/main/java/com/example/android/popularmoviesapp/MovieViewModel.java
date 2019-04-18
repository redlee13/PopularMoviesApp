package com.example.android.popularmoviesapp;

import android.app.Application;
import android.os.AsyncTask;

import com.example.android.popularmoviesapp.Models.MovieModel;
import com.example.android.popularmoviesapp.Util.JsonUtils;
import com.example.android.popularmoviesapp.Util.NetworkUtils;

import java.net.URL;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class MovieViewModel extends AndroidViewModel {
    private Database database;
    private MutableLiveData<List<MovieModel>> movieLiveData = new MutableLiveData<>();

    public MovieViewModel(Application application) {
        super(application);
        database = Database.getDatabase(application);
    }

    public LiveData<List<MovieModel>> getMovies(boolean fav, URL url) {
        if (fav) {
            new FavoriteTask().execute();
            return movieLiveData;
        } else {
            new MoviesFromServerTask().execute(url);
            return movieLiveData;
        }
    }

    private class MoviesFromServerTask extends AsyncTask<URL, Void, Void> {
        @Override
        protected Void doInBackground(URL... strings) {
            try {
                String response = NetworkUtils.getResponseFromHttpUrl(strings[0]);
                List<MovieModel> movies = JsonUtils.getJsonMovieList(response);
                movieLiveData.postValue(movies);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class FavoriteTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            movieLiveData.postValue(database.movieDAO().getAllMovies());
            return null;
        }
    }
}
