package com.eDigest.myThirdProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import com.eDigest.myThirdProject.Entity.User;
import com.eDigest.myThirdProject.Repository.UserRespository;

@Service
public class UserService {

	
	@Autowired
	private UserRespository userRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	public Optional<User> getentryByid(String id) {
		
		return userRepo.findById(id);
	}

	public void saveEntry(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}

	public List<User> listAllEntries() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
		
	}

	public void deleteEntryById(String id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
	}
	
	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

	public void deleteUserByUserName(String userName) {
		// TODO Auto-generated method stub
		userRepo.deleteByUserName(userName);
		
	}
	public String verify(User user) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
		 if(authentication.isAuthenticated()) {
			 return jwtService.generateToken(user.getUserName());
		 }
		 return "Failure";
	}
}
