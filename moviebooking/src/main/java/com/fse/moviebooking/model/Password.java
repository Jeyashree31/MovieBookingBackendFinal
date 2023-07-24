package com.fse.moviebooking.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Password {
	
	
	private String password;
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
