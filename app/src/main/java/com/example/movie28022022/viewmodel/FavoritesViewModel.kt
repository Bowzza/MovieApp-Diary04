package com.example.movie28022022.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movie28022022.models.Movie

class FavoritesViewModel : ViewModel() {
    private val _favoriteMovies = mutableListOf<Movie>()
//    val favoriteMovies: List<Movie>
//        get() = _favoriteMovies


    fun addMovie(movie: Movie) {
        _favoriteMovies.add(movie);
    }

    fun removeMovie(movie: Movie) {
        _favoriteMovies.remove(movie);
    }

    fun getAllMovies(): List<Movie> {
        return _favoriteMovies;
    }

    fun checkMovies(movie: Movie): Boolean {
        return _favoriteMovies.any{m -> m.id == movie.id}
    }

}