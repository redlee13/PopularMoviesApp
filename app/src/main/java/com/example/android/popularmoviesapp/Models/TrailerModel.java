package com.example.android.popularmoviesapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class TrailerModel implements Parcelable {

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
    private String mVideoKey;
    private String mVideoTitle;

    protected TrailerModel(Parcel in) {
        mVideoKey = in.readString();
        mVideoTitle = in.readString();
    }

    public TrailerModel(String mVideoKey, String mVideoTitle) {
        this.mVideoKey = mVideoKey;
        this.mVideoTitle = mVideoTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mVideoKey);
        dest.writeString(mVideoTitle);
    }

    public String getmVideoKey() {
        return mVideoKey;
    }

    public String getmVideoTitle() {
        return mVideoTitle;
    }
}
