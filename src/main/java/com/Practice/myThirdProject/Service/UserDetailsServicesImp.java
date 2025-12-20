package com.Practice.myThirdProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.Practice.myThirdProject.Entity.User;
import com.Practice.myThirdProject.Repository.UserRespository;

@Component
public class UserDetailsServicesImp implements UserDetailsService{

	@Autowired
	private UserRespository userRepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUserName(username);
		if(user!=null) {
			return org.springframework.security.core.userdetails.User
					.builder().username(user.getUserName())
					.password(user.getUserPassword())
					.roles(user.getRoles().toArray(new String[0]))
					.build();
			 
		}
		throw new UsernameNotFoundException("UserName not found" + username);
	}
	
}
