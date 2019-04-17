package com.example.android.popularmoviesapp.Util;

import android.util.Log;

import com.example.android.popularmoviesapp.Constants;
import com.example.android.popularmoviesapp.MovieModel;
import com.example.android.popularmoviesapp.TrailerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.http.Url;

public class JsonUtils {
    private static final String TAG = "JsonUtils";


    public static ArrayList<MovieModel> getJsonMovieList(String jsonMovieString)
            throws JSONException {
        final String RESULTS = "results";
        final String MOVIE_ID = "id";
        final String POSTER_PATH = "poster_path";
        final String OVERVIEW = "overview";
        final String RELEASE_DATE = "release_date";
        final String ORIGINAL_TITLE = "original_title";
        final String VOTE_AVERAGE = "vote_average";

        JSONObject movieJson = new JSONObject(jsonMovieString);
        JSONArray resultsArray = movieJson.getJSONArray(RESULTS);
        ArrayList<MovieModel> movieModelList = new ArrayList<>();
        for (int i=0; i < resultsArray.length(); i++){
            JSONObject results = resultsArray.getJSONObject(i);

            int movieID = results.getInt(MOVIE_ID);
            String originalTitle = results.getString(ORIGINAL_TITLE);
            String overview = results.getString(OVERVIEW);
            String releaseDate = results.getString(RELEASE_DATE);
            String posterPath = results.getString(POSTER_PATH);
            double voteAverage = results.getDouble(VOTE_AVERAGE);

            MovieModel movieModel = new MovieModel(movieID, originalTitle, overview, releaseDate, posterPath, voteAverage);
            movieModelList.add(movieModel);
        }

        return movieModelList;
    }

    public static ArrayList<TrailerModel> getJsonTrailerList(String jsonTrailerString)
        throws JSONException{
        final String RESULTS = "results";
        final String KEY = "key";
        final String NAME = "name";

        JSONObject movieJson = new JSONObject(jsonTrailerString);
        JSONArray resultsArray = movieJson.getJSONArray(RESULTS);
        ArrayList<TrailerModel> movieVideoList = new ArrayList<>();
        for (int i=0; i < resultsArray.length(); i++){
            JSONObject results = resultsArray.getJSONObject(i);

            String videoKey = results.getString(KEY);
            String videoTitle = results.getString(NAME);

            TrailerModel trailerModel = new TrailerModel(videoKey,videoTitle);
            movieVideoList.add(trailerModel);
        }
        return movieVideoList;
    }


}
