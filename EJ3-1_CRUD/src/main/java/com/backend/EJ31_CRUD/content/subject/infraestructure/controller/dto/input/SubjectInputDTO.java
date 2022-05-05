package com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectInputDTO {
    private String idSubject;
    private String name;
    private String coments;
}
