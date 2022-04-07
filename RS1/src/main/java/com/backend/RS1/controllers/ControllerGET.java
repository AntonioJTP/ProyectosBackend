package com.backend.RS1.controllers;

import com.backend.RS1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class ControllerGET {
    @Autowired
    PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById (@PathVariable Integer id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getPersonById (@PathVariable String name) {
        return ResponseEntity.ok(personService.getPersonByName(name));
    }
}
