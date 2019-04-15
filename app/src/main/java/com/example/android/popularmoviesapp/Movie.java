package com.example.android.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {



    private String mTitle;
    private String mOverview;
    private String mReleaseDate;
    private String mPosterPath;
    private double mVotes;


    protected Movie(Parcel in) {
        mTitle = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mPosterPath = in.readString();
        mVotes = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mOverview);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mPosterPath);
        parcel.writeDouble(mVotes);
    }

    public Movie(String title, String overview, String releaseDate, String posterPath, double voteAverage) {
        mTitle = title;
        mOverview = overview;
        mReleaseDate = releaseDate;
        mPosterPath = posterPath;
        mVotes = voteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public double getVotes() {
        return mVotes;
    }
}
