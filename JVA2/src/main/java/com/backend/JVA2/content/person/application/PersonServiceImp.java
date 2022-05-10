package com.backend.JVA2.content.person.application;

import com.backend.JVA2.content.person.domain.Person;
import com.backend.JVA2.content.person.infraestructure.controller.dto.input.PersonInputDTO;
import com.backend.JVA2.content.person.infraestructure.repository.services.PersonRepositoryService;
import com.backend.JVA2.content.person.application.errorHandler.exceptions.NotFoundException;
import com.backend.JVA2.content.person.application.errorHandler.exceptions.UnprocesableException;
import com.backend.JVA2.content.person.application.services.PersonService;
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
    public void createPerson(Person person) throws UnprocesableException {
        if (person == null) throw new UnprocesableException("No es posible insertar persona null");
        personRepositoryService.save(person);
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
        if (!Objects.equals(personInput.user(), null)) mainPerson.get().setUser(personInput.user());
        if (!Objects.equals(personInput.password(), null)) mainPerson.get().setPassword(personInput.password());
        if (!Objects.equals(personInput.name(), null)) mainPerson.get().setName(personInput.name());
        if (!Objects.equals(personInput.surname(), null)) mainPerson.get().setSurname(personInput.surname());
        if (!Objects.equals(personInput.companyEmail(), null)) mainPerson.get().setCompanyEmail(personInput.companyEmail());
        if (!Objects.equals(personInput.personalEmail(), null)) mainPerson.get().setPersonalEmail(personInput.personalEmail());
        if (!Objects.equals(personInput.city(), null)) mainPerson.get().setCity(personInput.city());
        if (!Objects.equals(personInput.active(), null)) mainPerson.get().setActive(personInput.active());
        if (!Objects.equals(personInput.createdDate(), null)) mainPerson.get().setCreatedDate(personInput.createdDate());
        if (!Objects.equals(personInput.terminationDate(), null)) mainPerson.get().setTerminationDate(personInput.terminationDate());
        if (!Objects.equals(personInput.imageUrl(), null)) mainPerson.get().setImageUrl(personInput.imageUrl());

        return personRepositoryService.save(mainPerson.get());
    }

    @Override
    public void deleteById(Long id) {
        personRepositoryService.delete(id);
    }
}
