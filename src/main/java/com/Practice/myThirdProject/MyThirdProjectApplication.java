package com.Practice.myThirdProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement		
public class MyThirdProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MyThirdProjectApplication.class, args);
		ConfigurableEnvironment configuration = context.getEnvironment();
		System.out.println("Environment currently set is " + configuration.getActiveProfiles()[0]);
		
	}
	
	
	@Bean
	public PlatformTransactionManager manage(MongoDatabaseFactory mongoDb) {
		return new MongoTransactionManager(mongoDb);
	}

}
