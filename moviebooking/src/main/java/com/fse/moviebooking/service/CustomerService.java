package com.fse.moviebooking.service;

import java.util.List;
import java.util.Optional;

import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.exception.UserAlreadyFoundException;
import com.fse.moviebooking.exception.UserNotFoundException;
import com.fse.moviebooking.model.Customer;

public interface CustomerService {
	
	public String addCustomer(Customer user) throws UserAlreadyFoundException, RoleException;

	public List<Customer> getAllCustomers();

	public Optional<Customer> getCustomerById(int loginId) throws UserNotFoundException;

	public Customer deleteCustomer(Customer user);

}
