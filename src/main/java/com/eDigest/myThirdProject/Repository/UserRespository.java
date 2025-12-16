package com.eDigest.myThirdProject.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eDigest.myThirdProject.Entity.User;

public interface UserRespository extends MongoRepository<User, String>{
	
	User findByUserName(String userName);

	User deleteByUserName(String userName); 

}
