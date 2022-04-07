package com.backend.BS2.controller;

import com.backend.BS2.Service.CityService;
import com.backend.BS2.Service.PersonService;
import com.backend.BS2.model.City;
import com.backend.BS2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller2 {

    @Autowired
    PersonService personService;

    @Autowired
    CityService cityService;

    @GetMapping("/controller2/getPerson")
    public Person getPerson() {
        personService.getPerson().setAge(personService.getPerson().getAge() * 2);
        return personService.getPerson();
    }

    @GetMapping("controller2/getCities")
    public List<City> getCities(){
        return cityService.getCities();
    }
}
