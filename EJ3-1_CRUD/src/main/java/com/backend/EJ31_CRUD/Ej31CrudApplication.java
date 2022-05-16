package com.backend.EJ31_CRUD;

import com.backend.EJ31_CRUD.jwt.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
@EnableFeignClients
public class Ej31CrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(Ej31CrudApplication.class, args);
	}
}

@EnableWebSecurity
@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/person/login").permitAll()
				.antMatchers("/h2-console").permitAll()
				.antMatchers(HttpMethod.GET, "/person").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/person/create").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/person").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/person").hasAnyRole("ADMIN")
				.anyRequest().authenticated();
	}
}
