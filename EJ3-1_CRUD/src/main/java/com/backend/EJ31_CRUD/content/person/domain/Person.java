package com.backend.EJ31_CRUD.content.person.domain;

import com.backend.EJ31_CRUD.content.person.infraestructure.controller.dto.input.PersonInputDTO;
import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import lombok.*;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSON")
    private Long idPerson;

    @NonNull
    @Size(min = 6, max = 10)
    @Column(name = "USER")
    private String user;

    @NonNull
    @Column(name = "PASSWORD")
    private String password;

    @NonNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @NonNull
    @Column(name = "COMPANY_EMAIL")
    private String companyEmail;

    @NonNull
    @Column(name = "PERSONAL_EMAIL")
    private String personalEmail;

    @NonNull
    @Column(name = "CITY")
    private String city;

    @NonNull
    @Column(name = "ACTIVE")
    private Boolean active;

    @NonNull
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "TERMINATION_DATE")
    private Date terminationDate;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "ADMIN")
    private Boolean admin;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Teacher teacher;

    public Person (PersonInputDTO personInputDTO) {
        setIdPerson(personInputDTO.getIdPerson());
        setUser(personInputDTO.getUser());
        setPassword(personInputDTO.getPassword());
        setName(personInputDTO.getName());
        setSurname(personInputDTO.getSurname());
        setCompanyEmail(personInputDTO.getCompanyEmail());
        setPersonalEmail(personInputDTO.getPersonalEmail());
        setCity(personInputDTO.getCity());
        setActive(personInputDTO.getActive());
        setCreatedDate(personInputDTO.getCreatedDate());
        setTerminationDate(personInputDTO.getTerminationDate());
        setImageUrl(personInputDTO.getImageUrl());

        if (personInputDTO.getAdmin() == null) {
            setAdmin(false);
        } else {
            setAdmin(personInputDTO.getAdmin());
        }
    }
}
