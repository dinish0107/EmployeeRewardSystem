package com.springboot.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.anyRequest().permitAll()
        .and()
        .csrf().disable()
        .cors().disable();
	}

	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

}