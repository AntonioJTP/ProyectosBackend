package com.backend.RS1.service;

import com.backend.RS1.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImp implements PersonService {
  List<Person> people = new ArrayList();

  @Override
  public List<Person> getPersonById(Integer id) {
    List<Person> personList = new ArrayList();

    people.forEach(key -> {
      if (Objects.equals(key.getId(), id)) {
        personList.add(key);
      }
    });
    return personList;
  }

  @Override
  public List<Person> getPersonByName(String name) {
    List<Person> personList = new ArrayList();

    people.forEach(key -> {
      if (Objects.equals(key.getName(), name)) {
        personList.add(key);
      }
    });
    return personList;
  }

  @Override
  public void addPerson(Person person) {
    people.add(person);
  }

  @Override
  public void updatePersonById(Integer id, Person person) {
    people.forEach(
        key -> {
          if (Objects.equals(key.getId(), id)) {
            if (person.getId() == null || !Objects.equals(person.getId(), key.getId()))
              System.out.println("No esta permitido modificar el ID de una persona");
            if (person.getName() != null && !Objects.equals(person.getName(), key.getName())) {
              key.setName(person.getName());
              System.out.println("Nombre Modificado");
            }
            if (person.getAge() != null && !Objects.equals(person.getAge(), key.getAge())) {
              key.setAge(person.getAge());
              System.out.println("Edad Modificada");
            }
            if (person.getCity() != null && !Objects.equals(person.getCity(), key.getCity())) {
              key.setCity(person.getCity());
              System.out.println("Ciudad Modificada");
            }
          }
        });
  }

  @Override
  public boolean deletePersonById(Integer id) {
    List<Person> personList = new ArrayList();

    people.forEach(
        key -> {
          if (Objects.equals(key.getId(), id)) {
            personList.add(key);
          }
        });

    if (personList.size() > 0) {
      personList.forEach(key -> people.remove(key));
      return true;
    }
    return false;
  }
}
