package com.backend.JVA2.content.person.infraestructure.controller.dto.input;

import java.util.Date;

public record PersonInputDTO(Long idPerson, String user, String password, String name, String surname,
                             String companyEmail, String personalEmail, String city, Boolean active, Date createdDate,
                             Date terminationDate, String imageUrl) {}
