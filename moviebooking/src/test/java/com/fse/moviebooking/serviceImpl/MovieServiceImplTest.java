package com.fse.moviebooking.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.model.Customer;
import com.fse.moviebooking.model.MovieDetails;
import com.fse.moviebooking.repo.MovieRepo;

@SpringBootTest
public class MovieServiceImplTest {

	@InjectMocks
	MovieServiceImpl movieServiceImpl;

	@Mock
	MovieRepo movieRepo;

	MovieDetails movie = new MovieDetails();

	@BeforeEach
	public void setUp() {
		movie.setMovieId(100);
		movie.setMovieName("ghhjk");
		movie.setTheatreName("PSR");
	}

	@Test
	public void addMoviesTest() throws RoleException {
		Mockito.when(movieRepo.save(movie)).thenReturn(movie);
		String email="Shree31@gmail.com";
		assertNotNull(movieServiceImpl.addMovies(movie,email));
		assertEquals("Movie Added successfully", movieServiceImpl.addMovies(movie,email));
	}

	@Test
	public void getAllMoviesTest() {
		List<MovieDetails> movies = new ArrayList<>();
		movies.add(movie);
		Mockito.when(movieRepo.findAll()).thenReturn(movies);
		assertNotNull(movieServiceImpl.getAllMovies());
		assertEquals(1, movieServiceImpl.getAllMovies().size());
	}
	
	@Test
	public void getMovieByNameTest() {
		List<MovieDetails> movies = new ArrayList<>();
		movies.add(movie);
		String movieName="ghhjk";
		Mockito.when(movieRepo.findByMovieName(movieName)).thenReturn(movies);
		assertNotNull(movieServiceImpl.getMovieByName(movieName));
	}
	
	@Test
	public void deleteMovieTestSuccess() throws NoMovieFoundException, RoleException {
		String email="Shree31@gmail.com";
		Optional<MovieDetails> movies=Optional.of(new MovieDetails());
		movies.get().setMovieId(100);
		movies.get().setMovieName("ghhjk");
		movies.get().setTheatreName("PSR");
		int movieId=100;
		String movieName="ghhjk";
		Mockito.when(movieRepo.findById(movieId)).thenReturn(movies);
		assertNotNull(movieServiceImpl.deleteMovie(movieName, movieId,email));
		assertEquals("Movie Deleted Successfully", movieServiceImpl.deleteMovie(movieName, movieId,email));
	}
	
	@Test
	public void deleteMovieTestFailWithNoMovie() {
		String email="Shree31@gmail.com";
		Optional<MovieDetails> movies=Optional.of(new MovieDetails());
		int movieId=100;
		String movieName="ghhjk";
		Mockito.when(movieRepo.findById(movieId)).thenReturn(movies);
		assertThrows(NoMovieFoundException.class, ()->movieServiceImpl.deleteMovie(movieName, movieId,email));
	}
	
	@Test
	public void deleteMovieTestFailWithIncorrectMovie() throws NoMovieFoundException {
		String email="Shree31@gmail.com";
		Optional<MovieDetails> movies=Optional.of(new MovieDetails());
		movies.get().setMovieId(100);
		movies.get().setMovieName("ghk");
		movies.get().setTheatreName("PSR");
		int movieId=100;
		String movieName="ghhjk";
		Mockito.when(movieRepo.findById(movieId)).thenReturn(movies);
		assertThrows(NoMovieFoundException.class, ()->movieServiceImpl.deleteMovie(movieName, movieId,email));
	}

}
