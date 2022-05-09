package com.backend.JVA2.person.application.services;

import com.backend.JVA2.person.domain.Person;
import com.backend.JVA2.person.infraestructure.controller.dto.input.PersonInputDTO;
import java.util.List;
import java.util.Optional;

public interface PersonService {
    void createPerson(Person person) throws Exception;
    Optional<Person> findById(Long id) throws Exception;
    List<Person> findByUser(String user) throws Exception;
    List<Person> findAll() throws Exception;
    Person updatePerson(Long id, PersonInputDTO person) throws Exception;
    void deleteById(Long id);
}
