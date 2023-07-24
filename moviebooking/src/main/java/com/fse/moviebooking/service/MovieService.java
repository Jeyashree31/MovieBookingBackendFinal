package com.fse.moviebooking.service;

import java.util.List;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.model.MovieDetails;

public interface MovieService {

	List<MovieDetails> getAllMovies();

	String addMovies(MovieDetails movies, String email) throws RoleException;

	List<MovieDetails> getMovieByName(String movieName);

	String deleteMovie(String movieName, int movieId, String email) throws NoMovieFoundException, RoleException;

}
