package com.example.android.popularmoviesapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class MovieModel implements Parcelable {

    @PrimaryKey
    private int mMovieId;
    private String mTitle;
    private String mOverview;
    private String mReleaseDate;
    private String mPosterPath;
    private String mVotes;

    public MovieModel(int movieId, String title, String overview, String releaseDate, String posterPath, String votes) {
        mMovieId = movieId;
        mTitle = title;
        mOverview = overview;
        mReleaseDate = releaseDate;
        mPosterPath = posterPath;
        mVotes = votes;
    }

    @Ignore
    protected MovieModel(Parcel in) {
        mMovieId = in.readInt();
        mTitle = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mPosterPath = in.readString();
        mVotes = in.readString();
    }

    @Ignore
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

    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mMovieId);
        parcel.writeString(mTitle);
        parcel.writeString(mOverview);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mPosterPath);
        parcel.writeString(mVotes);
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

    public String getVotes() {
        return mVotes;
    }

}
