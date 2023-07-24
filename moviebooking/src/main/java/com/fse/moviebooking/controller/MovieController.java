package com.fse.moviebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.model.MovieDetails;
import com.fse.moviebooking.service.MovieService;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/addMovies/{email}")
	public String addMovies(@RequestBody MovieDetails movies,@PathVariable String email) throws RoleException{
		return movieService.addMovies(movies,email);
	}
	
	@GetMapping("/all")
	public List<MovieDetails> getAllMovies(){
		return movieService.getAllMovies();
	}
	
	@GetMapping("/movies/search/{movieName}")
	public List<MovieDetails> getMovieByName(@PathVariable String movieName){
		return movieService.getMovieByName(movieName);
	}
	
	@DeleteMapping("/{movieName}/delete/{movieId}/{email}")
	public String deleteMovie(@PathVariable String movieName,@PathVariable int movieId,@PathVariable String email) throws NoMovieFoundException, RoleException{
		return movieService.deleteMovie(movieName,movieId,email);
	}

}
