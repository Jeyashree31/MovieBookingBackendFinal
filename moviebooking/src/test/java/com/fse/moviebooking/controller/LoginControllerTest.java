package com.fse.moviebooking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fse.moviebooking.exception.ActiveSessionException;
import com.fse.moviebooking.exception.InvalidCredentialsException;
import com.fse.moviebooking.model.Customer;
import com.fse.moviebooking.model.Login;
import com.fse.moviebooking.model.LoginResponse;
import com.fse.moviebooking.model.UpdatePassword;
import com.fse.moviebooking.repo.CustomerRepo;
import com.fse.moviebooking.repo.LoginRepo;
import com.fse.moviebooking.service.LoginService;

@SpringBootTest
public class LoginControllerTest {
	
	@Mock
	LoginService loginService;
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	CustomerRepo custRepo;
	
	@Mock
	LoginRepo loginRepo;
	
	Login user=new Login();
	
	@BeforeEach
	public void setUp() {
		user.setCustomerId("1");
		user.setEmail("abcd@gmail.com");
		user.setLoginStatus(true);
		user.setPassword("abcd");
	}
	
	@Test
	public void getAllLoggedInUsersTest() {
		List<Login> loggedInUsers=new ArrayList<>();
		loggedInUsers.add(user);
		when(loginService.getAllLoggedInUsers()).thenReturn(loggedInUsers);
		assertNotNull(loginController.getAllLoggedInusers());
	}
	
	@Test
	public void loginUserPassTest() throws InvalidCredentialsException, ActiveSessionException {
		String email="";
		String password="";
		Login login = new Login();
		when(loginService.loginUser(email, password)).thenReturn(new LoginResponse());
		assertEquals("UserLoggedIn Successfully", loginController.loginUser(login));
	}
	
	@Test
	public void forgetPasswordPassTest() throws InvalidCredentialsException {
		String email="";
		UpdatePassword password=new UpdatePassword();
		password.setPassword("abcde");
		when(loginService.forgetPassword("shreeravi@gmail.com","Shree@31")).thenReturn("New Password Changed Successfully");
		assertEquals("New Password Changed Successfully", loginController.forgetPassword(password));
	}
	
	
	@Test
	public void resetPasswordPassTest() throws InvalidCredentialsException {
		String email="";
		UpdatePassword password=new UpdatePassword();
		password.setPassword("abcde");
		when(loginService.resetPassword("shreeravi@gmail.com","Shree@31")).thenReturn("New Password reset Successfully");
		assertEquals("New Password reset Successfully", loginController.resetPassword(password));
	}
	
	
	@Test
	public void logoutPassTest() throws InvalidCredentialsException {
		String email="";
		when(loginService.logout(email)).thenReturn("User Loggedout Successfully");
		assertEquals("User Loggedout Successfully", loginController.logout(email));
	}
	

}
