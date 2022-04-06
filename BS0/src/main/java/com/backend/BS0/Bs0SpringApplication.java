package com.backend.BS0;

import com.backend.BS0.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Bs0SpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(Bs0SpringApplication.class, args);
	}

	@GetMapping("/user/{name}")
	public String sayHello(@PathVariable String name) {
		return String.format("Hello %s!", name);
	}

	@PostMapping("/useradd")
	public Person createPerson(@RequestBody Person person) {
		person.setAge(person.getAge() + 1);
		return person ;
	}
}
