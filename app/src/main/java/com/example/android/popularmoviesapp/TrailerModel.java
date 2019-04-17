package com.example.android.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

public class TrailerModel implements Parcelable {

    private String mVideoKey;
    private String mVideoTitle;


    protected TrailerModel(Parcel in) {
        mVideoKey = in.readString();
        mVideoTitle = in.readString();
    }

    public static final Creator<TrailerModel> CREATOR = new Creator<TrailerModel>() {
        @Override
        public TrailerModel createFromParcel(Parcel in) {
            return new TrailerModel(in);
        }

        @Override
        public TrailerModel[] newArray(int size) {
            return new TrailerModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mVideoKey);
        dest.writeString(mVideoTitle);
    }

    public TrailerModel(String mVideoKey, String mVideoTitle) {
        this.mVideoKey = mVideoKey;
        this.mVideoTitle = mVideoTitle;
    }

    public String getmVideoKey() {
        return mVideoKey;
    }

    public String getmVideoTitle() {
        return mVideoTitle;
    }
}
