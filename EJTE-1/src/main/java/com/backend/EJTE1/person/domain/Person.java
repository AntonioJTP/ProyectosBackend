package com.backend.EJTE1.person.domain;

import com.backend.EJTE1.person.infraestructure.controller.dto.input.PersonInputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Long idPerson;

    @NonNull
    private String user;

    @NonNull
    private String password;

    @NonNull
    private String name;

    private String surname;

    @NonNull
    @Column(name = "company_email")
    private String companyEmail;

    @NonNull
    @Column(name = "personal_email")
    private String personalEmail;

    @NonNull
    private String city;

    @NonNull
    private Boolean active;

    @NonNull
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "termination_date")
    private Date terminationDate;

    @Column(name = "image_url")
    private String imageUrl;
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
    }
}
