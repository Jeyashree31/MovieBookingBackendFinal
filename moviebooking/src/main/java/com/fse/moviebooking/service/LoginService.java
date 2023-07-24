package com.fse.moviebooking.service;

import java.util.List;

import com.fse.moviebooking.exception.ActiveSessionException;
import com.fse.moviebooking.exception.InvalidCredentialsException;
import com.fse.moviebooking.model.Login;
import com.fse.moviebooking.model.LoginResponse;
import com.fse.moviebooking.model.UpdatePassword;

public interface LoginService {

	LoginResponse loginUser(String email, String password) throws InvalidCredentialsException, ActiveSessionException;

	String logout(String email) throws InvalidCredentialsException;

//	String forgetPassword(UpdatePassword password) throws InvalidCredentialsException;

//	String resetPassword(UpdatePassword password) throws InvalidCredentialsException;

	List<Login> getAllLoggedInUsers();

	String forgetPassword(String email, String password) throws InvalidCredentialsException;

	String resetPassword(String email, String password) throws InvalidCredentialsException;

}
