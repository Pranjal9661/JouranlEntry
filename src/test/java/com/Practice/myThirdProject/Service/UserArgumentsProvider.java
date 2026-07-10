package com.Practice.myThirdProject.Service;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.security.core.userdetails.User;

public class UserArgumentsProvider implements ArgumentsProvider{

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		return Stream.of(
				Arguments.of(User.builder().username("Pranjal").password("12341234").build()),
				Arguments.of(User.builder().username("Hemant").password("12121212").build()));
	}

}
