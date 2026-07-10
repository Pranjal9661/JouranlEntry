package com.Practice.myThirdProject.SecurityConfig;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Practice.myThirdProject.JwtFilter.JwtFilter;
import com.Practice.myThirdProject.Service.UserDetailsServicesImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServicesImp userDetailsServicesImp;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SecurityConfig.class);
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		.cors(cors -> cors.disable())
		.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**","/api/user/**").authenticated()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/api/public/**").permitAll()
				.anyRequest().denyAll())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		  AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		try {
		        builder.userDetailsService(userDetailsServicesImp).passwordEncoder(passwordEncoder());

		} catch (Exception e) {
			// TODO: handle exception
			logger.info(" error occured for {}:  ",builder.getObject(), e);
			logger.warn(" error occured for {}:  ",builder.getObject(), e);
			logger.error(" error occured for {}:  ",builder.getObject(), e);
			logger.debug(" error occured for {}:  ",builder.getObject(), e);
			logger.trace(" error occured for {}:  ",builder.getObject(), e);
		
		}
	    return builder.build();
    }
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
