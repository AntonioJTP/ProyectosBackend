package com.backend.RS1.controllers;

import com.backend.RS1.model.Person;
import com.backend.RS1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person")
public class ControllerPUT {
    @Autowired
    PersonService personService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        personService.updatePersonById(id, person);
        return ResponseEntity.ok(personService.getPersonById(id));
    }
}
