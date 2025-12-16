package com.eDigest.myThirdProject.Controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eDigest.myThirdProject.Entity.User;
import com.eDigest.myThirdProject.Service.UserService;

@RestController
@RequestMapping("/api/public")
public class PublicContoller {
	
	@Autowired
	private UserService userService;
	
	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		try {
			 if(user.getUserName()!=null) {
				 User userTobeStored = new User(user.getUserName(), passwordEncoder.encode(user.getUserPassword()));
				 userTobeStored.setRoles(Arrays.asList("USER"));
				 userService.saveEntry(userTobeStored);
				 return new ResponseEntity<>(user,HttpStatus.OK);
			 }
		} catch (Exception e) {
			// TODO: handle exception
			throw new UsernameNotFoundException("user not found");
		}
		
		 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/verify")
	public String verifyAuthentication(@RequestBody User user) {
		return userService.verify(user);
	}
      
	@PutMapping("/update-user")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		try {
			Authentication auth =SecurityContextHolder.getContext().getAuthentication();
			String userName = auth.getName();
			User userinDb = userService.findByUserName(userName);
			userinDb.setUserName(user.getUserName());
			userinDb.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			userService.saveEntry(userinDb);
			return new ResponseEntity<>(HttpStatus.OK);	
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		
	}
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(){
		try {
			Authentication auth =SecurityContextHolder.getContext().getAuthentication();
			userService.deleteUserByUserName(auth.getName());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		
		
	}
}
