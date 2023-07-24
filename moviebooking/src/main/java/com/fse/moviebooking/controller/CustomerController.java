package com.fse.moviebooking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.exception.UserAlreadyFoundException;
import com.fse.moviebooking.exception.UserNotFoundException;
import com.fse.moviebooking.model.Customer;
import com.fse.moviebooking.repo.CustomerRepo;
import com.fse.moviebooking.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1.0/moviebooking")
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private CustomerRepo custRepo;
	 
	@PostMapping("/register")
	public String addCustomer(@RequestBody Customer user) throws UserAlreadyFoundException, RoleException {
		return custService.addCustomer(user);
	}
	
	@GetMapping("/getusers")
	public List<Customer> getAllCustomers() {
		return custService.getAllCustomers(); 
	}
	
	@GetMapping("/getuserById/{loginId}")
	public Optional<Customer> getCustomerById(@PathVariable int loginId) throws UserNotFoundException {
		return custService.getCustomerById(loginId);
	}
	
	@DeleteMapping("/deleteuser")
	public String deleteCustomer(@RequestBody Customer user) throws UserNotFoundException {
		String userStatus="";
		if(custService.deleteCustomer(user)!=null) {
			custRepo.delete(user);
			userStatus="User Deleted Successfully";
		}
		else {
			throw new UserNotFoundException("User Not Found.....");
		}
		return userStatus;
	}

}
