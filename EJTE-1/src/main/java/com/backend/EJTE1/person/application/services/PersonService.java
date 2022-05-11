package com.backend.EJTE1.person.application.services;

import com.backend.EJTE1.person.domain.Person;
import com.backend.EJTE1.person.infraestructure.controller.dto.input.PersonInputDTO;
import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person createPerson(Person person) throws Exception;
    Optional<Person> findById(Long id) throws Exception;
    List<Person> findByUser(String user) throws Exception;
    List<Person> findAll() throws Exception;
    Person updatePerson(Long id, PersonInputDTO person) throws Exception;

    void deleteById(Long id);
}
