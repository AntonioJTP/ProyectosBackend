package com.backend.BS2.Service;

import com.backend.BS2.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImp implements PersonService{
    Person person = new Person("Antonio Jose", "Jaen", 20);

    @Override
    public Person getPerson() {
        return person;
    }

}
