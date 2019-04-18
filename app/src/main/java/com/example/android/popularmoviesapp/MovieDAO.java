package com.example.android.popularmoviesapp;

import com.example.android.popularmoviesapp.Models.MovieModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MovieDAO {
    @Insert
    void insertMovie(MovieModel movieModel);

    @Query("DELETE FROM MovieModel WHERE mMovieId =:id")
    void deleteMovie(String id);

    @Query("Select * from MovieModel WHERE mMovieId = :id")
    MovieModel getSingleMovie (String id);

    @Query("Select * from MovieModel")
    List<MovieModel> getAllMovies();

}
