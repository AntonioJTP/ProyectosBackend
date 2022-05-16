package com.backend.EJ31_CRUD.content.person.infraestructure.controller;

import com.backend.EJ31_CRUD.content.person.application.errorHandler.CustomError;
import com.backend.EJ31_CRUD.content.person.application.errorHandler.exceptions.NotFoundException;
import com.backend.EJ31_CRUD.content.person.application.errorHandler.exceptions.UnprocesableException;
import com.backend.EJ31_CRUD.content.person.application.services.PersonService;
import com.backend.EJ31_CRUD.content.person.domain.Person;
import com.backend.EJ31_CRUD.content.person.infraestructure.controller.dto.input.PersonInputDTO;
import com.backend.EJ31_CRUD.content.person.infraestructure.controller.dto.output.PersonOutputDTO;
import com.backend.EJ31_CRUD.content.person.infraestructure.controller.dto.output.StudentPersonOutputDTO;
import com.backend.EJ31_CRUD.content.person.infraestructure.controller.dto.output.TeacherPersonOutputDTO;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.output.TeacherOutputDTO;
import com.backend.EJ31_CRUD.feign.Feign;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("person")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("user") String user, @RequestParam("password") String password) {
        List<Person> personList;
        try {
            personList = personService.findByUser(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se encuentra el usuario introducido");
        }

        if (personList.size() > 1) {
            return ResponseEntity.badRequest().body("Se ha encontrado mas de un usuario");
        }

        if (!personList.get(0).getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Contraseña incorrecta");
        }

        return ResponseEntity.ok(getJWTToken(user));
    }

    private String getJWTToken(String user) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(user)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @PostMapping("/create")
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

        if (oPerson.get().getStudent() != null) {
            StudentPersonOutputDTO studentPersonOutputDTO = new StudentPersonOutputDTO(oPerson.get());
            return ResponseEntity.ok(studentPersonOutputDTO);
        } else if (oPerson.get().getTeacher() != null) {
            TeacherPersonOutputDTO teacherPersonOutputDTO = new TeacherPersonOutputDTO(oPerson.get());
            return ResponseEntity.ok(teacherPersonOutputDTO);
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

        try {
            personService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No puedes eliminar una persona profesor si tiene estudiantes asignados");

        }
        return ResponseEntity.ok(personOutputDTO);
    }

    @GetMapping("restTemplate/{id}")
    public ResponseEntity<?> getTeacherOnPersonWithRestTemplate(@PathVariable String id) {
        ResponseEntity<?> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/teacher/" + id, TeacherOutputDTO.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(responseEntity.getBody());
        }

        return ResponseEntity.badRequest().body("Error en la peticion");
    }

    @Autowired
    Feign feign;

    @GetMapping("feign/{id}")
    public ResponseEntity<?> getTeacherOnPersonWithFeign(@PathVariable String id) {
        ResponseEntity<?> responseEntity = feign.getTeacherById(id);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(responseEntity.getBody());
        }

        return ResponseEntity.badRequest().body("Error en la peticion");
    }
}
