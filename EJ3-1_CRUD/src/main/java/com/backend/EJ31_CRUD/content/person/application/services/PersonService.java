package com.backend.EJ31_CRUD.content.person.application.services;

import com.backend.EJ31_CRUD.content.person.application.errorHandler.exceptions.NotFoundException;
import com.backend.EJ31_CRUD.content.person.application.errorHandler.exceptions.UnprocesableException;
import com.backend.EJ31_CRUD.content.person.domain.Person;
import com.backend.EJ31_CRUD.content.person.infraestructure.controller.dto.input.PersonInputDTO;
import java.util.List;
import java.util.Optional;

public interface PersonService {
    void createPerson(Person person) throws UnprocesableException;
    Optional<Person> findById(Long id) throws NotFoundException;
    List<Person> findByUser(String user) throws Exception;
    List<Person> findAll() throws Exception;
    Person updatePerson(Long id, PersonInputDTO person) throws Exception;
    void deleteById(Long id) throws Exception;
}
