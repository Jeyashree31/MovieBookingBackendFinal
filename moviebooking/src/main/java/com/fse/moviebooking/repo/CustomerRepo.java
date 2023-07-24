package com.fse.moviebooking.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fse.moviebooking.model.Customer;

@Repository
public interface CustomerRepo extends MongoRepository<Customer, Integer> {
	
	@Query("{email:'?0'}")
	Customer findByEmail(String email);

}
