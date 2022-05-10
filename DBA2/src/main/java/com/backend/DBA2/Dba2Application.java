package com.backend.DBA2;

import com.backend.DBA2.content.person.application.PersonService;
import com.backend.DBA2.content.person.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Dba2Application implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger("AppsDeveloperBlog");

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(Dba2Application.class, args);
	}

	@Override
	public void run(String... args) {
		personService.savePerson(new Person(1, "Antonio Jose", "Tirado Pancorbo", 21, "Jaen"));
		personService.savePerson(new Person(2, "Marta", "Bonoso", 19, "Madrid"));
		personService.savePerson(new Person(3, "Daniel", "Cabezas Civantos", 21, "Jaen"));

		LOG.info("Getting all data from MongoDB: {}", personService.getAllPerson());

		LOG.info("Getting person by id 2: {}", personService.findById(2));

		Person updatePerson = personService.findById(2);
		updatePerson.setSurname("de Dios");
		updatePerson.setAge(20);
		updatePerson.setCity("Jaen");
		LOG.info("Updating person by id 2: {}", personService.updatePerson(updatePerson));

		LOG.info("Remove person by id 3: {}", personService.deletePerson(personService.findById(3)));

		LOG.info("Getting all data from MongoDB: {}", personService.getAllPerson());
	}
}
