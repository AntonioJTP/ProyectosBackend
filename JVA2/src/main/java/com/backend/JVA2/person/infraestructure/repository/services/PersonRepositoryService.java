package com.backend.JVA2.person.infraestructure.repository.services;

import com.backend.JVA2.person.domain.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepositoryService {
    Person save(Person person);
    Optional<Person> findById(Long id);
    List<Person> findByUser(String user);
    List<Person> findAll();
    void delete(Long id);
}
