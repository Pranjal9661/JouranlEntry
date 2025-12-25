package com.Practice.myThirdProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Practice.myThirdProject.Entity.User;
import com.Practice.myThirdProject.Service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all-users")
	public ResponseEntity<?> listAll(){
		List<User> users=userService.listAllEntries();
		if(null!=users && !users.isEmpty()) {
			return new ResponseEntity<>(users,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
