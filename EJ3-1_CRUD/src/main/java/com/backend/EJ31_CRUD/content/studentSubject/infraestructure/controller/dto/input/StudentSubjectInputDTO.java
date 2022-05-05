package com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubjectInputDTO {
    private String idStudentSubject;
    private String idStudent;
    private String idSubject;
    private Date initialDate;
    private Date finishDate;
}
