package com.eDigest.myThirdProject.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eDigest.myThirdProject.Entity.User;
import com.eDigest.myThirdProject.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@GetMapping("/list")
	public List<User> getUsers() {
		return userService.listAllEntries();
	}
	
	@PostMapping("/addUsers")
	public void addUsers(@RequestBody User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setRoles(Arrays.asList("USER"));
		userService.saveEntry(user);
	}
	@PostMapping("/login")
	public void login(@RequestBody User user) {

		userService.verify(user);
	}
	
	@PutMapping("/updateUser/{userName}")
	public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName) {
		
		User userInDb = userService.findByUserName(userName);
		if(userInDb!=null) {
			userInDb.setUserName(user.getUserName());
			userInDb.setUserPassword(user.getUserPassword());
			userService.saveEntry(userInDb);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	
	
}
