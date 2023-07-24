package com.fse.moviebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.TicketNotFoundException;
import com.fse.moviebooking.model.TicketBooking;
import com.fse.moviebooking.service.BookingService;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/{movieName}/add")
	public String bookTickets(@RequestBody TicketBooking ticket,@PathVariable String movieName) throws NoMovieFoundException, TicketNotFoundException {
		return bookingService.bookTickets(ticket,movieName);
	}
	
	@GetMapping("/getAllTickets")
	public List<TicketBooking> getAllBookedTickets() {
		return bookingService.getAllBookedTickets();
	}
	
	@GetMapping("/getAllTicketsByMovie/{movieName}")
	public List<TicketBooking> getAllBookedTicketsByMovie(@PathVariable String movieName) {
		return bookingService.getAllBookedTicketsForMovie(movieName);
	}
	
	@PutMapping("/{movieName}/update/email")
	public String updateTicketStatus(String movieName,@RequestBody TicketBooking ticket) throws NoMovieFoundException {
		return bookingService.updateTicketStatus(movieName,ticket);
	}

}
