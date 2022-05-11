package com.backend.EJTE1.person.application;

import com.backend.EJTE1.person.application.errorHandler.exceptions.NotFoundException;
import com.backend.EJTE1.person.application.errorHandler.exceptions.UnprocesableException;
import com.backend.EJTE1.person.application.services.PersonService;
import com.backend.EJTE1.person.domain.Person;
import com.backend.EJTE1.person.infraestructure.controller.dto.input.PersonInputDTO;
import com.backend.EJTE1.person.infraestructure.repository.services.PersonRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonServiceImp implements PersonService {
    @Autowired
    PersonRepositoryService personRepositoryService;

    @Override
    public Person createPerson(Person person) throws UnprocesableException {
        if (person == null) throw new UnprocesableException("No es posible insertar persona null");
        return personRepositoryService.save(person);
    }

    @Override
    public Optional<Person> findById(Long id) throws NotFoundException {
        if (personRepositoryService.findById(id).isEmpty()) throw new NotFoundException("ID no valida");
        return personRepositoryService.findById(id);
    }

    @Override
    public List<Person> findByUser(String user) throws Exception {
        if (personRepositoryService.findByUser(user).size() == 0) throw new Exception();
        return personRepositoryService.findByUser(user);
    }

    @Override
    public List<Person> findAll() throws Exception {
        if (personRepositoryService.findAll().size() == 0) throw new Exception();
        return personRepositoryService.findAll();
    }

    @Override
    public Person updatePerson(Long id, PersonInputDTO personInput) throws Exception {
        Optional<Person> mainPerson = personRepositoryService.findById(id);
        if (mainPerson.isEmpty()) throw new NotFoundException("ID no valida");
        if (!Objects.equals(personInput.getUser(), null)) mainPerson.get().setUser(personInput.getUser());
        if (!Objects.equals(personInput.getPassword(), null)) mainPerson.get().setPassword(personInput.getPassword());
        if (!Objects.equals(personInput.getName(), null)) mainPerson.get().setName(personInput.getName());
        if (!Objects.equals(personInput.getSurname(), null)) mainPerson.get().setSurname(personInput.getSurname());
        if (!Objects.equals(personInput.getCompanyEmail(), null)) mainPerson.get().setCompanyEmail(personInput.getCompanyEmail());
        if (!Objects.equals(personInput.getPersonalEmail(), null)) mainPerson.get().setPersonalEmail(personInput.getPersonalEmail());
        if (!Objects.equals(personInput.getCity(), null)) mainPerson.get().setCity(personInput.getCity());
        if (!Objects.equals(personInput.getActive(), null)) mainPerson.get().setActive(personInput.getActive());
        if (!Objects.equals(personInput.getCreatedDate(), null)) mainPerson.get().setCreatedDate(personInput.getCreatedDate());
        if (!Objects.equals(personInput.getTerminationDate(), null)) mainPerson.get().setTerminationDate(personInput.getTerminationDate());
        if (!Objects.equals(personInput.getImageUrl(), null)) mainPerson.get().setImageUrl(personInput.getImageUrl());

        return personRepositoryService.save(mainPerson.get());
    }

    @Override
    public void deleteById(Long id) {
        personRepositoryService.delete(id);
    }
}
