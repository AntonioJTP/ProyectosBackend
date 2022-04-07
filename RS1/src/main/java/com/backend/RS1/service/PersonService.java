package com.backend.RS1.service;

import com.backend.RS1.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getPersonById(Integer id);
    List<Person> getPersonByName(String name);
    void addPerson(Person person);
    void updatePersonById(Integer id, Person person);
    boolean deletePersonById(Integer id);
}