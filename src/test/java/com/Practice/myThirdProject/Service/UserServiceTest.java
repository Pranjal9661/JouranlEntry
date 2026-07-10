package com.Practice.myThirdProject.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Practice.myThirdProject.Entity.User;
import com.Practice.myThirdProject.Repository.UserRespository;

@SpringBootTest
public class UserServiceTest {

	
	@Autowired 
	private UserRespository userRepo;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testFindUserByName() {
		assertNotNull(userRepo.findByUserName("Hemant"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"2,4,6",
		"1,3,7"
	})
	public void test(int a, int b, int c) {
		assertEquals(c, a + b);
	}
	
	@ParameterizedTest
	@ArgumentsSource(UserArgumentsProvider.class)
	public void testCustomArgumentSource(User user){
		assertTrue(userService.saveNewuser(user));
	}
}
