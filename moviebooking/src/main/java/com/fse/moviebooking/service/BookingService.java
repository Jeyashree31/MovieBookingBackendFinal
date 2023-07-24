package com.fse.moviebooking.service;

import java.util.List;

import com.fse.moviebooking.exception.NoMovieFoundException;
import com.fse.moviebooking.exception.TicketNotFoundException;
import com.fse.moviebooking.model.TicketBooking;

public interface BookingService {

	String bookTickets(TicketBooking ticket, String movieName) throws NoMovieFoundException, TicketNotFoundException;

	List<TicketBooking> getAllBookedTickets();

	String updateTicketStatus(String movieName, TicketBooking ticket) throws NoMovieFoundException;

	List<TicketBooking> getAllBookedTicketsForMovie(String movieName);

}
