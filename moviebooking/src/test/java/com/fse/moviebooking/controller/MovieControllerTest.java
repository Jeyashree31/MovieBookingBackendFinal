package com.fse.moviebooking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.model.MovieDetails;
import com.fse.moviebooking.service.MovieService;

@SpringBootTest
public class MovieControllerTest {
	
	@Mock
	MovieService movieService;
	
	@InjectMocks
	MovieController controller;
	
	MovieDetails movie=new MovieDetails();
	
	@BeforeEach
	public void setUp() {
		movie.setMovieId(100);
		movie.setMovieName("ghhjk");
		movie.setTheatreName("PSR");
	}
	
	@Test
	public void addMoviesSuccessTest() throws RoleException {
		String email="Shree31@gmail.com";
		when(movieService.addMovies(movie,email)).thenReturn("Movie Added successfully");
		assertEquals("Movie Added successfully", controller.addMovies(movie,email));
	}
	
	@Test
	public void getAllMoviesTest() {
		List<MovieDetails> movies=new ArrayList<>();
		movies.add(movie);
		when(movieService.getAllMovies()).thenReturn(movies);
		assertEquals(1, controller.getAllMovies().size());
	}
	
	@Test
	public void getByMovieNameTest() {
		List<MovieDetails> movies=new ArrayList<>();
		movies.add(movie);
		String movieName="ghhjk";
		when(movieService.getMovieByName(movieName)).thenReturn(movies);
		assertEquals(1, controller.getMovieByName(movieName).size());
	}
	
	@Test
	public void deleteMovieSuccessTest() throws NoMovieFoundException, RoleException {
		String movieName="ghhjk";
		String email="Shree31@gmail.com";
		int movieId=100;
		when(movieService.deleteMovie(movieName, movieId, movieName)).thenReturn("Movie Deleted successfully");
		assertEquals("Movie Deleted successfully", controller.deleteMovie(movieName, movieId,email));
	}

}
