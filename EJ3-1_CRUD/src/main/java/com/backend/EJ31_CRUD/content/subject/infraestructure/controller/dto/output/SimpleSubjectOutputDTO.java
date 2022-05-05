package com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleSubjectOutputDTO {
    private String idSubject;
    private String name;
    private String coments;

    public SimpleSubjectOutputDTO(Subject subject) {
        setIdSubject(subject.getIdSubject());
        setName(subject.getName());
        setComents(subject.getComents());
    }
}
