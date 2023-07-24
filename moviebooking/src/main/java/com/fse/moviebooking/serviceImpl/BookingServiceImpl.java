package com.fse.moviebooking.serviceImpl;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.TicketNotFoundException;
import com.fse.moviebooking.model.MovieDetails;
import com.fse.moviebooking.model.TicketBooking;
import com.fse.moviebooking.repo.BookingRepo;
import com.fse.moviebooking.repo.MovieRepo;
import com.fse.moviebooking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private MovieRepo movieRepo;

	@Autowired
	private BookingRepo bookingRepo;

	int ticketCount;

	Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Override
	public String bookTickets(TicketBooking ticket, String movieName)
			throws NoMovieFoundException, TicketNotFoundException {
		System.err.println(ticket);
		String random = UUID.randomUUID().toString();
		String ticketStatus = "";
		ticket.setMovieName(movieName);
		List<MovieDetails> moviesList = movieRepo.findAll();
		if (moviesList.size() >= 1) {
			for (MovieDetails movie : moviesList) {
				logger.info("Filtering movie based on movie and theatre name");
				if (movie.getMovieName().equalsIgnoreCase(movieName)
						&& movie.getTheatreName().equalsIgnoreCase(ticket.getTheatreName())) {
					ticketCount = movie.getTicketsAvailable() - ticket.getNumberOfTickets();
					System.err.println(ticketCount);
					logger.info("Checking for availability of seats");
					if (ticketCount >= 0) {
						ticket.setTicketId(random);
						System.err.println(ticket.getSeatNumbers());
						movie.setTicketsAvailable(ticketCount);
						if(movie.getTicketsAvailable()>0) {
							ticket.setTicketStatus("BOOK ASAP");
							movie.setTicketStatus("BOOK ASAP");
						}
						else {
							ticket.setTicketStatus("TICKETS SOLD");
							movie.setTicketStatus("TICKETS SOLD");
						}
						movieRepo.save(movie);
						bookingRepo.save(ticket);
						ticketStatus = "Ticket Booked Successfully";
					} else {
						ticket.setTicketStatus("TICKETS SOLD");
						movie.setTicketStatus("TICKETS SOLD");
						System.err.println(ticket.getTicketStatus());
						System.err.println(movie.getTicketStatus());
						movieRepo.save(movie);
						bookingRepo.save(ticket);
						throw new TicketNotFoundException("TICKETS SOLD");
					}
				}
			}
		} else {
			throw new NoMovieFoundException("No Movie Found");
		}
		return ticketStatus;
	}

	@Override
	public List<TicketBooking> getAllBookedTickets() {
		return bookingRepo.findAll();
	}

	@Override
	public List<TicketBooking> getAllBookedTicketsForMovie(String movieName) {
		System.out.println(movieName);
		return bookingRepo.findByMovieName(movieName);
	}

	@Override
	public String updateTicketStatus(String movieName, TicketBooking ticket) throws NoMovieFoundException {
		String ticketUpdateStatus = "";
		movieName = ticket.getMovieName();
		List<MovieDetails> moviesList = movieRepo.findByMovieName(movieName);
		if (moviesList.size() >= 1) {
			for (MovieDetails movie : moviesList) {
				logger.info("Updating ticket status based on availability");
				if (movie.getMovieName().equalsIgnoreCase(movieName)
						&& movie.getTheatreName().equalsIgnoreCase(ticket.getTheatreName())) {
					ticketUpdateStatus = (movie.getTicketsAvailable() >= 1) ? "BOOK ASAP" : "SOLD OUT";
					movie.setTicketStatus(ticketUpdateStatus);
					movieRepo.save(movie);
				}
			}
		} else {
			throw new NoMovieFoundException("No Movie Found");
		}

		return ticketUpdateStatus;
	}

}
