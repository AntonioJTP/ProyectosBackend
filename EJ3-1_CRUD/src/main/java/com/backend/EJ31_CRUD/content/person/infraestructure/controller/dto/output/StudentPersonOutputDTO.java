package com.backend.EJ31_CRUD.content.person.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.person.domain.Person;
import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.output.StudentOutputDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class StudentPersonOutputDTO {
    private Long idPerson;
    private String user;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private Date terminationDate;
    private String imageUrl;
    private StudentOutputDTO student;

    public StudentPersonOutputDTO(Person person) {
        setIdPerson(person.getIdPerson());
        setUser(person.getUser());
        setPassword(person.getPassword());
        setName(person.getName());
        setSurname(person.getSurname());
        setCompanyEmail(person.getCompanyEmail());
        setPersonalEmail(person.getPersonalEmail());
        setCity(person.getCity());
        setActive(person.getActive());
        setCreatedDate(person.getCreatedDate());
        setTerminationDate(person.getTerminationDate());
        setImageUrl(person.getImageUrl());
        setStudent(new StudentOutputDTO(person.getStudent()));
    }
}
