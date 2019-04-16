package com.example.android.popularmoviesapp;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MovieDAO {
    @Insert
    public void insertMovie(MovieModel movieModel);

    @Query("DELETE FROM MovieModel WHERE mMovieId =:id")
    void deleteMovie(String id);

    @Query("Select * from MovieModel WHERE mMovieId = :id")
    public MovieModel getSingleMovie (String id);

    @Query("Select * from MovieModel")
    public List<MovieModel> getAllMovies();

}
