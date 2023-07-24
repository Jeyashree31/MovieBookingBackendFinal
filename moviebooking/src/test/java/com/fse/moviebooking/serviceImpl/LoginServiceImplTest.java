package com.fse.moviebooking.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fse.moviebooking.exception.ActiveSessionException;
import com.fse.moviebooking.exception.InvalidCredentialsException;
import com.fse.moviebooking.model.Customer;
import com.fse.moviebooking.model.Login;
import com.fse.moviebooking.model.UpdatePassword;
import com.fse.moviebooking.repo.CustomerRepo;
import com.fse.moviebooking.repo.LoginRepo;

@SpringBootTest
public class LoginServiceImplTest {

	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	@Mock
	LoginRepo loginRepo;

	@Mock
	CustomerRepo custRepo;

	@Mock
	Login logindata;

	@BeforeEach
	public void setUp() {
		logindata.setCustomerId("10HU1");
		logindata.setEmail("abcd@gmail.com");
		logindata.setPassword("abcd");
		logindata.setLoginStatus(true);
	}

	@Test
	public void getAllLoggedInUsersTest() {
		List<Login> loggedInUsers = new ArrayList<>();
		loggedInUsers.add(logindata);
		Mockito.when(loginRepo.findAll()).thenReturn(loggedInUsers);
		assertNotNull(loginServiceImpl.getAllLoggedInUsers());
		assertEquals(1, loginServiceImpl.getAllLoggedInUsers().size());
	}

	@Test
	public void loginUserTestInvalidCredentials() {
		List<Login> loggedInUsers = new ArrayList<>();
		loggedInUsers.add(logindata);
		String email = "abcd@gmail.com";
		String password = "abcd";
		Mockito.when(custRepo.findByEmail(email)).thenReturn(null);
		Mockito.when(loginRepo.findByEmail(email)).thenReturn(null);
		assertThrows(InvalidCredentialsException.class, ()->loginServiceImpl.loginUser(email, password));
	}

	@Test
	public void loginUserTestActiveSession() throws InvalidCredentialsException, ActiveSessionException {
		String email = "abcd@gmail.com";
		String password = "abcd";
		Mockito.when(custRepo.findByEmail(email)).thenReturn(null);
		Mockito.when(loginRepo.findByEmail(email)).thenReturn(logindata);
		assertThrows(ActiveSessionException.class,()->loginServiceImpl.loginUser(email, password));
	}

	@Test
	public void loginUserTestSuccess() throws InvalidCredentialsException, ActiveSessionException {
		String email = "abcd@gmail.com";
		String password = "abcd";

		Customer customer = new Customer();
		customer.setLoginId(1);
		customer.setFirstname("abc");
		customer.setLastName("d");
		customer.setEmail("abcd@gmail.com");
		customer.setPassword("abcd");
		customer.setConfirmPassword("abc");
		customer.setContactNumber("9876543234");

		Mockito.when(custRepo.findByEmail(email)).thenReturn(customer);
		Mockito.when(loginRepo.findByEmail(email)).thenReturn(null);
		Mockito.when(loginRepo.save(logindata)).thenReturn(logindata);
		assertEquals("UserLoggedIn Successfully", loginServiceImpl.loginUser(email, password));

	}

	@Test
	public void forgetPasswordTestSuccess() throws InvalidCredentialsException {

		Customer customer = new Customer();
		customer.setLoginId(1);
		customer.setFirstname("abc");
		customer.setLastName("d");
		customer.setEmail("abcd@gmail.com");
		customer.setPassword("abcd");
		customer.setConfirmPassword("abc");
		customer.setContactNumber("9876543234");

		String email = "abcd@gmail.com";
		String password="abcd";

		Mockito.when(custRepo.findByEmail(email)).thenReturn(customer);
		Mockito.when(loginRepo.findByEmail(email)).thenReturn(null);
		assertEquals("New Password Changed Successfully", loginServiceImpl.forgetPassword(email, password));
	}

	@Test
	public void forgetPasswordTestFail() {
		String email = "abcd@gmail.com";
		String password="abcd";
		Mockito.when(custRepo.findByEmail(email)).thenReturn(null);
		Mockito.when(loginRepo.findByEmail(email)).thenReturn(null);
		assertThrows(InvalidCredentialsException.class, ()->loginServiceImpl.forgetPassword(email, password));
	}
	
	@Test
	public void resetPasswordTestSuccess() throws InvalidCredentialsException {

		Customer customer = new Customer();
		customer.setLoginId(1);
		customer.setFirstname("abc");
		customer.setLastName("d");
		customer.setEmail("abcd@gmail.com");
		customer.setPassword("abcd");
		customer.setConfirmPassword("abc");
		customer.setContactNumber("9876543234");

		String email = "abcd@gmail.com";

		String password="abcd";

		Mockito.when(custRepo.findByEmail(email)).thenReturn(customer);
		Mockito.when(loginRepo.findByEmail(email)).thenReturn(logindata);
		assertEquals("New Password reset Successfully", loginServiceImpl.resetPassword(email, password));
	}
	
	@Test
	public void resetPasswordTestFail() {

		Customer customer = new Customer();
		customer.setLoginId(1);
		customer.setFirstname("abc");
		customer.setLastName("d");
		customer.setEmail("abcd@gmail.com");
		customer.setPassword("abcd");
		customer.setConfirmPassword("abc");
		customer.setContactNumber("9876543234");

		String email = "abcd@gmail.com";
		
		String password="abcd";
		
		Mockito.when(custRepo.findByEmail(email)).thenReturn(null);
		Mockito.when(loginRepo.findByEmail(email)).thenReturn(logindata);
		assertThrows(InvalidCredentialsException.class, ()->loginServiceImpl.resetPassword(email, password));
	}

	@Test
	public void logoutTestSuccess() throws InvalidCredentialsException {
		String email = "abcd@gmail.com";
		Login login=new Login();
		login.setCustomerId("ghj23");
		login.setEmail("abcd@gmail.com");
		login.setLoginStatus(true);
		login.setPassword("fgdh56");
		Mockito.when(loginRepo.findByEmail(email)).thenReturn(login);
		assertEquals("User Loggedout Successfully", loginServiceImpl.logout(email));
	}

	@Test
	public void logoutTestFail() {
		String email = "abcd@gmail.com";
		Mockito.when(loginRepo.findByEmail(email)).thenReturn(logindata);
		logindata.setLoginStatus(false);
		assertThrows(InvalidCredentialsException.class,()-> loginServiceImpl.logout(email));
	}


}
