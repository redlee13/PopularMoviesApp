package com.example.android.popularmoviesapp;

import android.content.Context;
import android.os.AsyncTask;

import com.example.android.popularmoviesapp.Models.MovieModel;

public class FarvoriteMovieWorker {
    private boolean setAsFavorite = true;
    private Database database;

    public FarvoriteMovieWorker(Context context) {
        database = Database.getDatabase(context);
    }

    public void insertFavMovie (MovieModel movie){
        setAsFavorite = true;
        new FavoriteTask().execute(movie);
    }

    public void deleteFavMovie(MovieModel movie){
        setAsFavorite = false;
        new FavoriteTask().execute(movie);
    }

    private class FavoriteTask extends AsyncTask<MovieModel, Void, Void>{
        @Override
        protected Void doInBackground(MovieModel... movieModels) {
            if (setAsFavorite){
                database.movieDAO().insertMovie(movieModels[0]);
            } else {
                database.movieDAO().deleteMovie(movieModels[0].getMovieId());
            }
            return null;
        }
    }
}
