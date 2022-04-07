package com.backend.RS1.controllers;

import com.backend.RS1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class ControllerDELETE {
    @Autowired
    PersonService personService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePerson(@PathVariable Integer id) {
        if (personService.deletePersonById(id)) return ResponseEntity.ok("Persona eliminada");

        return ResponseEntity.ok("Persona no encontrada");
    }
}
