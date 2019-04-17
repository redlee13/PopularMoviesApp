package com.example.android.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

@Entity
public class MovieModel implements Parcelable {


    private int mMovieId;
    private String mTitle;
    private String mOverview;
    private String mReleaseDate;
    private String mPosterPath;
    private double mVotes;

    protected MovieModel(Parcel in) {
        mMovieId = in.readInt();
        mTitle = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mPosterPath = in.readString();
        mVotes = in.readDouble();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mMovieId);
        parcel.writeString(mTitle);
        parcel.writeString(mOverview);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mPosterPath);
        parcel.writeDouble(mVotes);
    }

    public MovieModel(int movieId, String title, String overview, String releaseDate, String posterPath, double voteAverage) {
        mMovieId = movieId;
        mTitle = title;
        mOverview = overview;
        mReleaseDate = releaseDate;
        mPosterPath = posterPath;
        mVotes = voteAverage;
    }


    public int getMovieId() {
        return mMovieId;
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
