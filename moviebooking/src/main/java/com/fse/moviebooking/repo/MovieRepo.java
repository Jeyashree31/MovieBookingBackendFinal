package com.fse.moviebooking.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fse.moviebooking.model.MovieDetails;

@Repository
public interface MovieRepo extends MongoRepository<MovieDetails, Integer> {

	@Query("{movieName:'?0'}")
	List<MovieDetails> findByMovieName(String movieName);

	@Query("{$and:[{movieName:'?0',theatreName:'?0'}]}")
	MovieDetails findByMovieAndTheatreName(String movieName, String theatreName);
}
