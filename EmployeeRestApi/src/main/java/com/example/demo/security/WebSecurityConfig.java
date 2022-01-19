package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api").permitAll()
    	.antMatchers("/api/user","/api/role/").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.POST, "/api/user", "/api/role").hasAuthority("admin")
		.antMatchers(HttpMethod.GET, "/api/user", "/api/role").hasAuthority("admin")
		.antMatchers(HttpMethod.GET, "/api/employees").hasAnyAuthority("admin")
		.antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("admin")				
		.antMatchers(HttpMethod.PUT, "/api/employees").hasAuthority("admin")
		.antMatchers(HttpMethod.DELETE, "/api/employees/*").hasAuthority("admin")
		.antMatchers(HttpMethod.GET, "/api/employees/search/*").hasAuthority("admin")
		.antMatchers(HttpMethod.GET, "/api/employees/sort").hasAuthority("admin")
		.antMatchers("/api").hasAuthority("admin")
		.anyRequest().authenticated()
		.and().httpBasic()
		.and()
        .cors().and().csrf().disable();
	}
}
