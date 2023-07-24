package com.fse.moviebooking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.TicketNotFoundException;
import com.fse.moviebooking.model.MovieDetails;
import com.fse.moviebooking.model.TicketBooking;
import com.fse.moviebooking.service.BookingService;

@SpringBootTest
public class BookingControllerTest {
	
	@Mock
	BookingService bookingService;
	
	@InjectMocks
	BookingController controller;
	
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
	public void bookTicketsSuccessTest() throws NoMovieFoundException, TicketNotFoundException {
		String movieName="";
		when(bookingService.bookTickets(ticket, movieName)).thenReturn("Ticket Booked Successfully");
		assertEquals("Ticket Booked Successfully", controller.bookTickets(ticket, movieName));
	}
	
	@Test
	public void getAllBookedTicketsTest() {
		List<TicketBooking> bookedTickets=new ArrayList<>();
		bookedTickets.add(ticket);
		when(bookingService.getAllBookedTickets()).thenReturn(bookedTickets);
		assertEquals(1, controller.getAllBookedTickets().size());
	}
	
	@Test
	public void updateTicketStatusTest() throws NoMovieFoundException {
		String movieName="ghhjk";
		when(bookingService.updateTicketStatus(movieName, ticket)).thenReturn("BOOK ASAP");
		assertNotNull(controller.updateTicketStatus(movieName, ticket));
	}
}
