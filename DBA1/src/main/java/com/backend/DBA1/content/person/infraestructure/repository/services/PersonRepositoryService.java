package com.backend.DBA1.content.person.infraestructure.repository.services;

import com.backend.DBA1.content.person.domain.Person;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PersonRepositoryService {
    Person save(Person person);
    Optional<Person> findById(Long id);
    List<Person> findByUser(String user);
    List<Person> findAll();
    void delete(Long id);
    List<Person> getData(HashMap<String, Object> conditions, String order);
}
