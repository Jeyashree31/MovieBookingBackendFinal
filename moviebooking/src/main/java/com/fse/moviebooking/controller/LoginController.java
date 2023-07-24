package com.fse.moviebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.moviebooking.exception.ActiveSessionException;
import com.fse.moviebooking.exception.InvalidCredentialsException;
import com.fse.moviebooking.model.Login;
import com.fse.moviebooking.model.LoginResponse;
import com.fse.moviebooking.model.UpdatePassword;
import com.fse.moviebooking.service.LoginService;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/allLoggedInUsers")
	public List<Login> getAllLoggedInusers(){
		return loginService.getAllLoggedInUsers();
	}
	
	@PostMapping("/login")
	public LoginResponse loginUser(@RequestBody Login login) throws InvalidCredentialsException, ActiveSessionException {
		return loginService.loginUser(login.getEmail(),login.getPassword());
	}
	  
	@PostMapping("/forgot")
	public String forgetPassword(@RequestBody UpdatePassword password) throws InvalidCredentialsException {
		return loginService.forgetPassword(password.getEmail(),password.getPassword());
	}
	
	@PostMapping("/reset")
	public String resetPassword(@RequestBody UpdatePassword password) throws InvalidCredentialsException {
		return loginService.resetPassword(password.getEmail(),password.getPassword());
	}

	@GetMapping("/logout/{email}")
	public String logout(@PathVariable String email) throws InvalidCredentialsException {
		return loginService.logout(email);
	} 
}
