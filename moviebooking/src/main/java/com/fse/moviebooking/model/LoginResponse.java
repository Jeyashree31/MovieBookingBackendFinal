package com.fse.moviebooking.model;

public class LoginResponse {
	private String role;
	private String loggedInStatus;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLoggedInStatus() {
		return loggedInStatus;
	}

	public void setLoggedInStatus(String loggedInStatus) {
		this.loggedInStatus = loggedInStatus;
	}

	@Override
	public String toString() {
		return "LoginResponse [role=" + role + ", loggedInStatus=" + loggedInStatus + "]";
	}
}