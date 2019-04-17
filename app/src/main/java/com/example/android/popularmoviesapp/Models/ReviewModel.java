package com.example.android.popularmoviesapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ReviewModel implements Parcelable {

    private String mAuthor;
    private String mContent;

    protected ReviewModel(Parcel in) {
        mAuthor = in.readString();
        mContent = in.readString();
    }

    public static final Creator<ReviewModel> CREATOR = new Creator<ReviewModel>() {
        @Override
        public ReviewModel createFromParcel(Parcel in) {
            return new ReviewModel(in);
        }

        @Override
        public ReviewModel[] newArray(int size) {
            return new ReviewModel[size];
        }
    };

    public String getAuthor() {
        return mAuthor;
    }

    public String getContent() {
        return mContent;
    }

    public ReviewModel(String author, String content) {
        mAuthor = author;
        mContent = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mAuthor);
        parcel.writeString(mContent);
    }
}
