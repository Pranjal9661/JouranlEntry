package com.Practice.myThirdProject.Service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import com.Practice.myThirdProject.Entity.User;  	
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.Practice.myThirdProject.Repository.UserRespository;

public class UserDetailsSerrviceImplTests {

	@Autowired
	private UserDetailsServicesImp userDetailsImpl;
	
	@Mock
	private UserRespository userRepo;
	
	@BeforeEach
	void setUp(){
		
		MockitoAnnotations.openMocks(this);  // initMocks is depreceated in Spring boot 3.4.1 ownwards.
	}
	
	@Test
	public void loadUserByUserName() {
		when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Pranjal").userPassword("12341234").roles(new ArrayList<>()).build());
		UserDetails userDtls =  userDetailsImpl.loadUserByUsername("RAM");
	}
}
