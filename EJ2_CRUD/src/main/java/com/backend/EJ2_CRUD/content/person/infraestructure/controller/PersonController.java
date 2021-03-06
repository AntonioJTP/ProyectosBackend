package com.backend.EJ2_CRUD.content.person.infraestructure.controller;

import com.backend.EJ2_CRUD.content.person.application.errorHandler.CustomError;
import com.backend.EJ2_CRUD.content.person.application.errorHandler.exceptions.NotFoundException;
import com.backend.EJ2_CRUD.content.person.application.errorHandler.exceptions.UnprocesableException;
import com.backend.EJ2_CRUD.content.person.application.services.PersonService;
import com.backend.EJ2_CRUD.content.person.domain.Person;
import com.backend.EJ2_CRUD.content.person.infraestructure.controller.dto.input.PersonInputDTO;
import com.backend.EJ2_CRUD.content.person.infraestructure.controller.dto.output.PersonOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonInputDTO personInputDTO) {
        Person person = new Person(personInputDTO);

        try {
            personService.createPerson(person);
        } catch (UnprocesableException u) {
            return ResponseEntity.status(422).body(new CustomError(new Date(), 422, u.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        PersonOutputDTO personOutputDTO = new PersonOutputDTO(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personOutputDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllPerson() {
        List<PersonOutputDTO> outputPersonList = new ArrayList();

        try {
            personService.findAll().forEach(key -> outputPersonList.add(new PersonOutputDTO(key)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No hay personas registradas");
        }

        return ResponseEntity.ok(outputPersonList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {
        Optional<Person> oPerson = Optional.empty();

        try {
            oPerson = personService.findById(id);
        } catch (NotFoundException n) {
            return ResponseEntity.status(404).body(new CustomError(new Date(), 404, n.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        PersonOutputDTO personOutputDTO = new PersonOutputDTO(oPerson.get());
        return ResponseEntity.ok(personOutputDTO);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<?> getPersonByUser(@PathVariable String user) {
        List<PersonOutputDTO> outputPersonList = new ArrayList();

        try {
            personService.findByUser(user).forEach(key -> outputPersonList.add(new PersonOutputDTO(key)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No hay personas con ese nombre registradas");
        }

        return ResponseEntity.ok(outputPersonList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonById(@PathVariable Long id, @RequestBody PersonInputDTO personInputDTO) {
        Person personOutput = null;

        try {
            personOutput = personService.updatePerson(id, personInputDTO);
        } catch (NotFoundException n) {
            return ResponseEntity.status(404).body(new CustomError(new Date(), 404, n.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        PersonOutputDTO personOutputDTO = new PersonOutputDTO(personOutput);
        return ResponseEntity.ok(personOutputDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable Long id) {
        Optional<Person> oPerson = Optional.empty();

        try {
            oPerson = personService.findById(id);
        } catch (NotFoundException n) {
            return ResponseEntity.status(404).body(new CustomError(new Date(), 404, n.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        PersonOutputDTO personOutputDTO = new PersonOutputDTO(oPerson.get());

        personService.deleteById(id);
        return ResponseEntity.ok(personOutputDTO);
    }
}
