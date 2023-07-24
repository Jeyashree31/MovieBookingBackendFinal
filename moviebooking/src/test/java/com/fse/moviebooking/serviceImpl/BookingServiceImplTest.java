package com.fse.moviebooking.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.TicketNotFoundException;
import com.fse.moviebooking.model.MovieDetails;
import com.fse.moviebooking.model.TicketBooking;
import com.fse.moviebooking.repo.BookingRepo;
import com.fse.moviebooking.repo.MovieRepo;

@SpringBootTest
public class BookingServiceImplTest {
	
	@InjectMocks
	BookingServiceImpl bookingServiceImpl;
	
	@Mock
	MovieRepo movieRepo;
	
	@Mock
	BookingRepo bookingRepo;
	
	TicketBooking ticket=new TicketBooking();
	@BeforeEach
	public void setUp() {
		List<String> seatNumbers=new ArrayList<>();
		seatNumbers.add("H1");
		seatNumbers.add("H2");
		ticket.setMovieName("ghhjk");
		ticket.setTheatreName("PSR");
		ticket.setNumberOfTickets(2);
		ticket.setSeatNumbers(seatNumbers);
	}
	
	@Test
	public void bookTicketsTest() throws NoMovieFoundException, TicketNotFoundException {
		MovieDetails movie = new MovieDetails();
		movie.setMovieId(100);
		movie.setMovieName("ghhjk");
		movie.setTheatreName("PSR");
		List<MovieDetails> moviesList=new ArrayList<>();
		moviesList.add(movie);
		when(movieRepo.findAll()).thenReturn(moviesList);
		String movieName="ghhjk";
		assertEquals(bookingServiceImpl.bookTickets(ticket, movieName),"Ticket Booked Successfully");
	}
	
	@Test
	public void bookTicketsTestNoTicket() throws NoMovieFoundException {
		MovieDetails movie = new MovieDetails();
		movie.setMovieId(100);
		movie.setMovieName("ghhjk");
		movie.setTheatreName("PSR");
		movie.setTicketsAvailable(1);
		List<MovieDetails> moviesList=new ArrayList<>();
		moviesList.add(movie);
		when(movieRepo.findAll()).thenReturn(moviesList);
		String movieName="ghhjk";
		assertThrows(TicketNotFoundException.class,()->bookingServiceImpl.bookTickets(ticket, movieName));
	}
	
	@Test
	public void bookTicketsTestNoMovie() {
		List<MovieDetails> moviesList=new ArrayList<>();
		when(movieRepo.findAll()).thenReturn(moviesList);
		String movieName="ghhjk";
		assertThrows(NoMovieFoundException.class,()->bookingServiceImpl.bookTickets(ticket, movieName),"No Movie found..");
	}
	
	@Test
	public void getAllBookedTicketsTest() {
		MovieDetails movie = new MovieDetails();
		movie.setMovieId(100);
		movie.setMovieName("ghhjk");
		movie.setTheatreName("PSR");
		movie.setTicketsAvailable(10);
		TicketBooking ticketBooking=new TicketBooking();
		ticketBooking.setMovieName("ghhjk");
		ticketBooking.setTheatreName("PSR");
		ticketBooking.setNumberOfTickets(2);
		List<String> seatNumbers=new ArrayList<>();
		seatNumbers.add("H1");
		seatNumbers.add("H2");
		ticketBooking.setSeatNumbers(seatNumbers);
		List<TicketBooking> bookedTickets=new ArrayList<>();
		bookedTickets.add(ticketBooking);
		when(bookingRepo.findAll()).thenReturn(bookedTickets);
		assertNotNull(bookingServiceImpl.getAllBookedTickets());
	}
	
	@Test
	public void updateTicketStatusTest() throws NoMovieFoundException {
		MovieDetails movie = new MovieDetails();
		movie.setMovieId(100);
		movie.setMovieName("ghhjk");
		movie.setTheatreName("PSR");
		movie.setTicketsAvailable(10);
		List<MovieDetails> moviesList=new ArrayList<>();
		moviesList.add(movie);
		String movieName="ghhjk";
		when(movieRepo.findByMovieName(movieName)).thenReturn(moviesList);
		assertEquals(bookingServiceImpl.updateTicketStatus(movieName, ticket), "BOOK ASAP");
	}
	
	@Test
	public void updateTicketStatusTestWithNoSeats() throws NoMovieFoundException {
		MovieDetails movie = new MovieDetails();
		movie.setMovieId(100);
		movie.setMovieName("ghhjk");
		movie.setTheatreName("PSR");
		movie.setTicketsAvailable(0);
		List<MovieDetails> moviesList=new ArrayList<>();
		moviesList.add(movie);
		String movieName="ghhjk";
		when(movieRepo.findByMovieName(movieName)).thenReturn(moviesList);
		assertEquals(bookingServiceImpl.updateTicketStatus(movieName, ticket), "SOLD OUT");
	}
	
	@Test
	public void updateTicketStatusTestWithNoMovie() throws NoMovieFoundException {
		List<MovieDetails> movies=new ArrayList<>();
		String movieName="ghhjk";
		when(movieRepo.findByMovieName(movieName)).thenReturn(movies);
		assertThrows(NoMovieFoundException.class, ()->bookingServiceImpl.updateTicketStatus(movieName, ticket));
	}

}
