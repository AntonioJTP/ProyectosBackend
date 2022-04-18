package com.backend.BS3;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Bs3Application {
	public static void main(String[] args) {
		SpringApplication.run(Bs3Application.class, args);
	}

	@PostConstruct
	public void sendInicialClass() {
		System.out.println("Hola desde la clase inicial");
	}

  @Bean
  public CommandLineRunner sendSecondClass() {
		return p -> {
		  System.out.println("Hola desde la segunda clase");
		};
	}

	@Bean
	public CommandLineRunner sendThirdClass(ApplicationArguments args) {

		return p -> {
			System.out.println("Hola desde la tercera clase");
			System.out.println("Argumentos: " + Arrays.toString(args.getSourceArgs()));
		};
	}
}
