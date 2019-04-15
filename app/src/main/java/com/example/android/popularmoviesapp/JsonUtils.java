package com.example.android.popularmoviesapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
    private static final String TAG = "JsonUtils";


    public static ArrayList<Movie> getJsonMovieList(String jsonMovieString)
            throws JSONException {

        final String RESULTS = "results";
        final String POSTER_PATH = "poster_path";
        final String OVERVIEW = "overview";
        final String RELEASE_DATE = "release_date";
        final String ORIGINAL_TITLE = "original_title";
        final String VOTE_AVERAGE = "vote_average";

        JSONObject movieJson = new JSONObject(jsonMovieString);

        // TODO later, if there is an error or no internet

        JSONArray resultsArray = movieJson.getJSONArray(RESULTS);

        ArrayList<Movie> movieList = new ArrayList<>();
        for (int i=0; i < resultsArray.length(); i++){
            JSONObject results = resultsArray.getJSONObject(i);

            String originalTitle = results.getString(ORIGINAL_TITLE);
            String overview = results.getString(OVERVIEW);
            String releaseDate = results.getString(RELEASE_DATE);
            String posterPath = results.getString(POSTER_PATH);
            double voteAverage = results.getDouble(VOTE_AVERAGE);

            Movie movie = new Movie(originalTitle, overview, releaseDate, posterPath, voteAverage);
            movieList.add(movie);

        }

        return movieList;
    }


}
