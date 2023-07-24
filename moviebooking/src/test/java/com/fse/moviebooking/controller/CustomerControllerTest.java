package com.fse.moviebooking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.exception.UserAlreadyFoundException;
import com.fse.moviebooking.exception.UserNotFoundException;
import com.fse.moviebooking.model.Customer;
import com.fse.moviebooking.service.CustomerService;

@SpringBootTest
public class CustomerControllerTest {
	
	@Mock
	CustomerService custService;
	
	@InjectMocks
	CustomerController controller;
	
	Customer user=new Customer();
	
	@Test
	public void addCustomerTestPass() throws UserAlreadyFoundException, RoleException {
		user.setLoginId(1);
		user.setFirstname("abc");
		user.setLastName("d");
		user.setEmail("abcd@gmail.com");
		user.setPassword("abcd");
		user.setConfirmPassword("abcd");
		user.setContactNumber("9876543234");
//		when(custService.addCustomer(user)).thenReturn(user);
		assertEquals("User Added Successfully", controller.addCustomer(user));
	}
	
	@Test
	public void addCustomerTestFail() throws UserAlreadyFoundException, RoleException {
		user.setLoginId(1);
		user.setFirstname("abc");
		user.setLastName("d");
		user.setEmail("abcd@gmail.com");
		user.setPassword("abcd");
		user.setConfirmPassword("abc");
		user.setContactNumber("9876543234");
		when(custService.addCustomer(user)).thenReturn(null);
		assertEquals("password and confirmpassword must be same", controller.addCustomer(user));
	}
	
	@Test
	public void getAllCustomersTest() {
		List<Customer> users = new ArrayList<>();
		users.add(user);
		Mockito.when(custService.getAllCustomers()).thenReturn(users);
		List<Customer> result=custService.getAllCustomers();
		assertNotNull(controller.getAllCustomers());
		assertEquals(1, result.size());
	}
	
	@Test
	public void getCustomerByIdTest() throws UserNotFoundException {
		Optional<Customer> user=Optional.of(new Customer());
		user.get().setLoginId(1);
		user.get().setFirstname("abc");
		user.get().setLastName("d");
		user.get().setEmail("abcd@gmail.com");
		user.get().setPassword("abcd");
		user.get().setConfirmPassword("abc");
		user.get().setContactNumber("9876543234");
		int loginId=1;
		Mockito.when(custService.getCustomerById(loginId)).thenReturn(user);
		assertNotNull(controller.getCustomerById(loginId));
	}

}
