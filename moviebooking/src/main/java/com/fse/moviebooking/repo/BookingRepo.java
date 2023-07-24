package com.fse.moviebooking.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fse.moviebooking.model.MovieDetails;
import com.fse.moviebooking.model.TicketBooking;

@Repository
public interface BookingRepo extends MongoRepository<TicketBooking, String>{
	
	@Query("{movieName:'?0'}")
	List<TicketBooking> findByMovieName(String movieName);
}
