package com.backend.BS2.controller;

import com.backend.BS2.Service.CityService;
import com.backend.BS2.Service.PersonService;
import com.backend.BS2.model.City;
import com.backend.BS2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
//@Configuration
public class Controller1 {

    @Autowired
    PersonService personService;

    @Autowired
    CityService cityService;

    @Autowired
    @Qualifier("bean1")
    Person person;

    @Autowired
    @Qualifier("bean2")
    Person person2;

    @Autowired
    @Qualifier("bean3")
    Person person3;

    @GetMapping("/controller1/getPerson")
    public Person getPerson() {
        return personService.getPerson();
    }

    @PostMapping("/controller1/addCity")
    public boolean addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    @GetMapping("/controller1/bean/{bean}")
    public Person getBeanPerson(@PathVariable String bean) {
        if (Objects.equals(bean, "bean1"))
            return person;
        else if (Objects.equals(bean, "bean2"))
            return person2;
        else if (Objects.equals(bean, "bean3"))
            return person3;
        else
            return null;
    }
}
