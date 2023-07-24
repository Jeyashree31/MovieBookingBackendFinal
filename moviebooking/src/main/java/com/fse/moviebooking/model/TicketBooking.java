package com.fse.moviebooking.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document("TicketBooking")
public class TicketBooking {

	@Id
	private String ticketId;
	//	private MovieDetails movieDetails;
	private String movieName;
	private String theatreName;
	private int numberOfTickets;
	private List<String> SeatNumbers;
	private String ticketStatus;
	private String email;

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	
//	public MovieDetails getMovieDetails() {
//		return movieDetails;
//	}
//
//	public void setMovieDetails(MovieDetails movieDetails) {
//		this.movieDetails = movieDetails;
//	}
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public List<String> getSeatNumbers() {
		return SeatNumbers;
	}

	public void setSeatNumbers(List<String> seatNumbers) {
		SeatNumbers = seatNumbers;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "TicketBooking [ticketId=" + ticketId + ", movieName=" + movieName + ", theatreName=" + theatreName
				+ ", numberOfTickets=" + numberOfTickets + ", SeatNumbers=" + SeatNumbers + ", ticketStatus="
				+ ticketStatus + ", email=" + email + "]";
	}

//	@Override
//	public String toString() {
//		return "TicketBooking [ticketId=" + ticketId + ", movieName=" + movieName + ", theatreName=" + theatreName
//				+ ", numberOfTickets=" + numberOfTickets + ", SeatNumbers=" + SeatNumbers + "]";
//	}

}
