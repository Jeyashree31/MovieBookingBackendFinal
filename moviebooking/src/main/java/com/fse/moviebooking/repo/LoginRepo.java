package com.fse.moviebooking.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fse.moviebooking.model.Login;

public interface LoginRepo extends MongoRepository<Login, String> {
	
	@Query("{email:'?0'}")
	Login findByEmail(String email);

}
