package com.springboot.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.main.service.UserService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getProvider());
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/hr/add","/managers/address/add").permitAll()
		.antMatchers(HttpMethod.GET,"/user/login").authenticated()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and()
		.csrf().disable()
		.cors().disable();
		
	}

	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	public DaoAuthenticationProvider getProvider() {
		System.out.println("getprovide called............");
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		//also,i want spring to know that i have encrypted password in DB
		dao.setPasswordEncoder(getEncoder());
		//from here..iwnt spring to go to my db and fetch users
		dao.setUserDetailsService(userService);//UserDetailsService:UserService
		return dao;
	}
}
