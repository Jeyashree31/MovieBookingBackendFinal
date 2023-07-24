package com.fse.moviebooking.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.moviebooking.exception.ActiveSessionException;
import com.fse.moviebooking.exception.InvalidCredentialsException;
import com.fse.moviebooking.model.Customer;
import com.fse.moviebooking.model.Login;
import com.fse.moviebooking.model.LoginResponse;
import com.fse.moviebooking.model.Password;
import com.fse.moviebooking.model.UpdatePassword;
import com.fse.moviebooking.repo.CustomerRepo;
import com.fse.moviebooking.repo.LoginRepo;
import com.fse.moviebooking.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	private Login logindata;

	private String loginStatus="";
	
	Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Override
	public List<Login> getAllLoggedInUsers() {
		return loginRepo.findAll();
	}
	
	public LoginResponse loginUser(String email, String password) throws InvalidCredentialsException, ActiveSessionException {
		Customer customer=custRepo.findByEmail(email);
		logger.info("Checking for login status");
		LoginResponse response = new LoginResponse();
		response.setRole(customer.getRole());
		if(customer!=null && customer.getPassword().equals(password)) {
			logindata.setEmail(email);
			logindata.setPassword(password);
			logindata.setLoginStatus(true);
			loginRepo.save(logindata);
			response.setLoggedInStatus("UserLoggedIn Successfully");
		}
//		else if(loginRepo.findByEmail(email)!=null) {
//			response.setLoggedInStatus("User already have active session");
//			throw new ActiveSessionException("User already have active session");
//		}
		else {
			logger.info("checking for user credentials");
			response.setLoggedInStatus("Invalid data..Please check your credentials");
			throw new InvalidCredentialsException("Invalid data..Please check your credentials");
		}
		return response;
	}

	@Override
	public String forgetPassword(String email,String password) throws InvalidCredentialsException {
		String passwordUpdate="";
		Login logindata=loginRepo.findByEmail(email);
		System.err.println(logindata);
		Customer customerdata = custRepo.findByEmail(email);
		logger.info("checking for user data and login status");
		if(logindata==null && customerdata!=null) {
			custRepo.delete(customerdata);
			customerdata.setPassword(password);	
			customerdata.setConfirmPassword(password);
			custRepo.save(customerdata);
			passwordUpdate="New Password Changed Successfully";
		}
		else {
			throw new InvalidCredentialsException("Invalid request");
		}
		return passwordUpdate;
	}
	
	@Override
	public String resetPassword(String email,String password) throws InvalidCredentialsException {
		String passwordUpdate="";
		Login logindata=loginRepo.findByEmail(email);
		Customer customerdata = custRepo.findByEmail(email);
		if(customerdata!=null && logindata!=null) {
			logindata.setPassword(password);
			customerdata.setPassword(password);	
			customerdata.setConfirmPassword(password);
			custRepo.save(customerdata);
			passwordUpdate="New Password reset Successfully";
		}
		else {
			throw new InvalidCredentialsException("Invalid request");
		}
		return passwordUpdate;
	}

	@Override
	public String logout(String email) throws InvalidCredentialsException {
		String logout="";
		Login logindata=loginRepo.findByEmail(email);
		if(logindata!=null && logindata.isLoginStatus()==true) {
			logout="User Loggedout Successfully";
			loginRepo.delete(logindata);
		}
		else {
			throw new InvalidCredentialsException("Invalid request");
		}
		return logout;
	}


}
