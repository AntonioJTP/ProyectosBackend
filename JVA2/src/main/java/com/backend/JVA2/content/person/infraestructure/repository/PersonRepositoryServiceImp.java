package com.backend.JVA2.content.person.infraestructure.repository;

import com.backend.JVA2.content.person.domain.Person;
import com.backend.JVA2.content.person.infraestructure.repository.jpa.PersonRepositoryJPA;
import com.backend.JVA2.content.person.infraestructure.repository.services.PersonRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonRepositoryServiceImp implements PersonRepositoryService {
    @Autowired
    PersonRepositoryJPA personRepositoryJPA;

    @Override
    public Person save(Person person) {
        return personRepositoryJPA.save(person);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepositoryJPA.findById(id);
    }

    @Override
    public List<Person> findByUser(String user) {
        return personRepositoryJPA.findByUser(user);
    }

    @Override
    public List<Person> findAll() {
        return personRepositoryJPA.findAll();
    }

    @Override
    public void delete(Long id) {
        personRepositoryJPA.deleteById(id);
    }
}
