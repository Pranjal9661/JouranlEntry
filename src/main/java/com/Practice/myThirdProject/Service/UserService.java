package com.Practice.myThirdProject.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.Practice.myThirdProject.Entity.User;
import com.Practice.myThirdProject.Repository.UserRespository;

import lombok.extern.slf4j.Slf4j;

	

@Service
@Slf4j
public class UserService {

	
	@Autowired
	private UserRespository userRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
	public Optional<User> getentryByid(String id) {
		
		return userRepo.findById(id);
	}

	public void saveEntry(User user) {
		// TODO Auto-generated method stub
		try {
			userRepo.save(user);
		}
		catch (Exception e) {
			// TODO: handle exception
				log.info(" error Ocurred for User: {} " + user.getUserName());
		}
		
	}
	public boolean saveNewuser(User user) {
		try {
			user.setUserName(user.getUserName());
			user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			userRepo.save(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.info(" error Ocurred for User: {} " + user.getUserName()); 
			return false;
		}
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

	public void saveAdmin(User user) {
		// TODO Auto-generated method stub
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setRoles(Arrays.asList("ADMIN","USER"));
		userRepo.save(user);
		
	}


}
