package com.fse.moviebooking.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.exception.UserAlreadyFoundException;
import com.fse.moviebooking.exception.UserNotFoundException;
import com.fse.moviebooking.model.Customer;
import com.fse.moviebooking.repo.CustomerRepo;

@SpringBootTest
public class CustomerServiceImplTest {

	@InjectMocks
	CustomerServiceImpl custServiceImpl;

	@Mock
	CustomerRepo custrepo;

	Customer user = new Customer();

	@BeforeEach
	public void setUp() {

		user.setLoginId(1);
		user.setFirstname("abc");
		user.setLastName("d");
		user.setEmail("abcd@gmail.com");
		user.setPassword("abcd");
		user.setConfirmPassword("abcd");
		user.setContactNumber("9876543234");
	}

	@Test
	public void addCustomerTestPass() throws UserAlreadyFoundException, RoleException {
		Mockito.when(custrepo.save(user)).thenReturn(user);
		assertNotNull(custServiceImpl.addCustomer(user));
	}

	@Test
	public void addCustomerTestFail() throws UserAlreadyFoundException, RoleException {
		Customer mockUser = new Customer();
		user.setLoginId(1);
		user.setFirstname("abc");
		user.setLastName("d");
		user.setEmail("abcd@gmail.com");
		user.setPassword("abcd");
		user.setConfirmPassword("abc");
		user.setContactNumber("9876543234");
		Mockito.when(custrepo.save(user)).thenReturn(mockUser);
		assertNull(custServiceImpl.addCustomer(user));
	}

	@Test
	public void getAllCustomersTest() {
		List<Customer> users = new ArrayList<>();
		users.add(user);
		Mockito.when(custrepo.findAll()).thenReturn(users);
		assertNotNull(custServiceImpl.getAllCustomers());
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
		Mockito.when(custrepo.findById(loginId)).thenReturn(user);
		assertNotNull(custServiceImpl.getCustomerById(loginId));
	}

}
