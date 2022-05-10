package com.backend.DBA2.content.person.application;

import com.backend.DBA2.content.person.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PersonServiceImp implements PersonService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void savePerson(Person person) {
        mongoTemplate.save(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return mongoTemplate.findAll(Person.class);
    }

    @Override
    public Person findById(Integer id) {
        return mongoTemplate.findById(id, Person.class);
    }

    @Override
    public Person updatePerson(Person person) {
        return mongoTemplate.save(person);
    }

    @Override
    public Person deletePerson(Person person) {
        mongoTemplate.remove(person);
        return person;
    }
}
