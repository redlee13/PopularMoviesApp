package com.example.android.popularmoviesapp.Util;

import android.net.Uri;
import android.util.Log;

import com.example.android.popularmoviesapp.BuildConfig;
import com.example.android.popularmoviesapp.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the weather servers.
 */
public final class NetworkUtils {
    private static final String TAG = "NetworkUtils";

    public static String baseUrl = Constants.BASE_URL_POPULAR;

    /* The API KEY we want to enter */
    private static final String API_KEY = BuildConfig.ApiKey;
    /* The language we want our API to return */
    private static final String language = "en_US";
    /* The page number we want our API to return */
    public static int displayPage = 1;

    private final static String MOVIE_PARAM = "videos";
    private final static String API_PARAM = "api_key";
    private final static String LANG_PARAM = "language";
    private final static String PAGE_PARAM = "page";


    public static URL buildUrl() {
        Uri builtUri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter(API_PARAM, API_KEY)
                .appendQueryParameter(LANG_PARAM, language)
                .appendQueryParameter(PAGE_PARAM, Integer.toString(displayPage))
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);
        return url;
    }

    public static URL buildTrailerUrl(String trailerID) {
        Uri builtUri = Uri.parse(Constants.BASE_URL_TRAILER).buildUpon()
                .appendPath(trailerID)
                .appendPath(MOVIE_PARAM)
                .appendQueryParameter(API_PARAM, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);
        return url;
    }
    public static URL buildYoutubeUrl(String youtubeKey){
        Uri builtUri = Uri.parse(Constants.YOUTUBE_BASE_URL).buildUpon()
                .appendPath(youtubeKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);
        return url;
    }


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}