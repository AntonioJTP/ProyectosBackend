package com.backend.RS1.controllers;

import com.backend.RS1.model.Person;
import com.backend.RS1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class ControllerPOST {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok(person);
    }
}
