package com.backend.EJTE1.person.infraestructure.repository.services;

import com.backend.EJTE1.person.domain.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepositoryService {
    Person save(Person person);
    Optional<Person> findById(Long id);
    List<Person> findByUser(String user);
    List<Person> findAll();
    void delete(Long id);
}
