package com.backend.DBA2.content.person.application;

import com.backend.DBA2.content.person.domain.Person;
import java.util.List;

public interface PersonService {
    void savePerson(Person person);
    List<Person> getAllPerson();
    Person findById(Integer id);
    Person updatePerson(Person person);
    Person deletePerson(Person person);
}
