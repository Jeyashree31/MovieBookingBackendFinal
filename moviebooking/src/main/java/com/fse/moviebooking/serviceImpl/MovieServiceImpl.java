package com.fse.moviebooking.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.model.Customer;
import com.fse.moviebooking.model.MovieDetails;
import com.fse.moviebooking.repo.CustomerRepo;
import com.fse.moviebooking.repo.MovieRepo;
import com.fse.moviebooking.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private CustomerRepo custRepo;
	
	Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

	
	@Override
	public String addMovies(MovieDetails movie,String email) throws RoleException {
		String moviemessage="";
		Customer findByEmail = custRepo.findByEmail(email);
		if(findByEmail!=null && findByEmail.getRole().equalsIgnoreCase("Admin")) {
			movieRepo.save(movie);
			moviemessage = "Movie Updated successfully";
		}
		
		else{
			throw new RoleException("User not having access to add movies");
		}
		 
		return moviemessage;

	}

	@Override
	public List<MovieDetails> getAllMovies() {
		return movieRepo.findAll();
	}

	@Override
	public List<MovieDetails> getMovieByName(String movieName) {
		logger.info("Filtering by movie name");
		List<MovieDetails> movieList = movieRepo.findByMovieName(movieName);
		return movieList;
	}

	@Override
	public String deleteMovie(String movieName, int movieId,String email) throws NoMovieFoundException, RoleException {
		Optional<MovieDetails> findMovieById = movieRepo.findById(movieId);
		String movieDeleteStatus="";
		logger.info("Checking for movie ID and movie Name");
		Customer findByEmail = custRepo.findByEmail(email);
		if(findByEmail!=null && findByEmail.getRole().equalsIgnoreCase("Admin")) {
		if(findMovieById.isPresent() && movieName.equalsIgnoreCase(findMovieById.get().getMovieName())) {
			movieRepo.delete(findMovieById.get());
			movieDeleteStatus="Movie Deleted Successfully";
		}
		else {
			throw new NoMovieFoundException("No such Id/Movie available..Please enter correct ID and MovieName to proceed");
		}
		}
		else {
			throw new RoleException("User not having access to delete movies");
		}
		
		return movieDeleteStatus;
	}

}