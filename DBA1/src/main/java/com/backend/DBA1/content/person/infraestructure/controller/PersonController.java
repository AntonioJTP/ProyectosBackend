package com.backend.DBA1.content.person.infraestructure.controller;

import com.backend.DBA1.content.person.application.errorHandler.CustomError;
import com.backend.DBA1.content.person.application.errorHandler.exceptions.NotFoundException;
import com.backend.DBA1.content.person.application.errorHandler.exceptions.UnprocesableException;
import com.backend.DBA1.content.person.application.services.PersonService;
import com.backend.DBA1.content.person.domain.Person;
import com.backend.DBA1.content.person.infraestructure.controller.dto.input.PersonInputDTO;
import com.backend.DBA1.content.person.infraestructure.controller.dto.output.PersonOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import java.util.*;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    EntityManager entityManager;

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

    @GetMapping("/search")
    public ResponseEntity<?> getData(@RequestParam(required = false) String user, @RequestParam(required = false) String name, @RequestParam(required = false) String surname,
                                     @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-mm-dd") String created_date, @RequestParam(required = false, defaultValue = "greaterThan") String condition,
                                     @RequestParam(required = false) String order, @RequestParam(defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size) {

        HashMap<String, Object> predicates = new HashMap<>();

        if (user != null) predicates.put("user", user);
        if (name != null) predicates.put("name", name);
        if (surname != null) predicates.put("surname", surname);
        if (!condition.equals("greaterThan") && !condition.equals("lessThan") && !condition.equals("equal")) condition = "greaterThan";
        if (created_date != null) {
            predicates.put("createdDate", created_date);
            predicates.put("condition", condition);
        }

        List<Person> data = personService.getData(predicates, order);

        List<PersonOutputDTO> personOutputDTOList = new ArrayList<>();

        data.forEach(key -> {
            personOutputDTOList.add(new PersonOutputDTO(key));
        });

        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = (Math.min((start + pageable.getPageSize()), personOutputDTOList.size()));
        Page<PersonOutputDTO> pageOutPut = new PageImpl<PersonOutputDTO>(personOutputDTOList.subList(start, end), pageable, personOutputDTOList.size());

        return new ResponseEntity<>(pageOutPut, HttpStatus.OK);
    }
}
