package com.fse.moviebooking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(value="Login")
@Component
public class Login {

	@Id
	@JsonIgnore
	private String customerId;
	private String email;
	private String password;
	private boolean loginStatus;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	@Override
	public String toString() {
		return "Login [customerId=" + customerId + ", email=" + email + ", password=" + password + ", loginStatus="
				+ loginStatus + "]";
	}
}
	
	