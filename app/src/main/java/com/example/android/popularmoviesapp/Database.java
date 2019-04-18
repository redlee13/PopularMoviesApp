package com.example.android.popularmoviesapp;

import android.content.Context;

import com.example.android.popularmoviesapp.Models.MovieModel;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = MovieModel.class, version = 2, exportSchema = false)
public abstract class Database extends RoomDatabase{
    public abstract MovieDAO movieDAO();

    private static Database database;

    public static Database getDatabase(Context context) {
        if (database == null){
            database = Room.databaseBuilder(context, Database.class, "Favorite_movie.db")
                    .fallbackToDestructiveMigration().build();
        }
        return database;
    }
}
