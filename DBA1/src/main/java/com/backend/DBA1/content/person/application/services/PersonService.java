package com.backend.DBA1.content.person.application.services;

import com.backend.DBA1.content.person.domain.Person;
import com.backend.DBA1.content.person.infraestructure.controller.dto.input.PersonInputDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PersonService {
    void createPerson(Person person) throws Exception;
    Optional<Person> findById(Long id) throws Exception;
    List<Person> findByUser(String user) throws Exception;
    List<Person> findAll() throws Exception;
    Person updatePerson(Long id, PersonInputDTO person) throws Exception;
    void deleteById(Long id);
    List<Person> getData(HashMap<String, Object> conditions, String order);
}
