package com.backend.BS2;

import com.backend.BS2.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Bs2SpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bs2SpringApplication.class, args);
	}

	@Bean("bean1")
	public Person createPerson() {
		return new Person("David", "Jaen", 21);
	}

	@Bean("bean2")
	public Person createPerson2() {
		return new Person("Antonio", "Jaen", 20);
	}

	@Bean("bean3")
	public Person createPerson3() {
		return new Person("Marta", "Madrid", 20);
	}
}
