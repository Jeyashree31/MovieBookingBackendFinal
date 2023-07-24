package com.fse.moviebooking.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.moviebooking.exception.RoleException;
import com.fse.moviebooking.exception.UserAlreadyFoundException;
import com.fse.moviebooking.exception.UserNotFoundException;
import com.fse.moviebooking.model.Customer;
import com.fse.moviebooking.repo.CustomerRepo;
import com.fse.moviebooking.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo custrepo;
	
	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	public String addCustomer(Customer user) throws UserAlreadyFoundException, RoleException {
		logger.info("Checking for password criteria");
		String userStatus="";
		if(custrepo.findByEmail(user.getEmail())!=null) {
			throw new UserAlreadyFoundException("User Already Exists Exception");
		}
		else if(user.getRole()==null) {
			throw new RoleException("Role should be selected");
		}
		else {
			custrepo.save(user); 
			userStatus="User Added Successfully";
		}
		return userStatus;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return custrepo.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(int loginId) throws UserNotFoundException {
		logger.info("Filtering customers by ID");
		if(custrepo.findById(loginId).isEmpty()) {
			throw new UserNotFoundException("No User Exists");
		}
		else {
			return custrepo.findById(loginId);
		}
		
	}

	@Override
	public Customer deleteCustomer(Customer user) {
		logger.info("Checking for user");
		Customer findByEmail = custrepo.findByEmail(user.getEmail());
		System.err.println(findByEmail);
		return findByEmail;
	}

}
