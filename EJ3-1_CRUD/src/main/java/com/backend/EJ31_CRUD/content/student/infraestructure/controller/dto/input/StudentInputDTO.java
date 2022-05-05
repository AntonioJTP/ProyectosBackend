package com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDTO {
    private String idStudent;
    private Integer hoursWeek;
    private String branch;
    private String coments;
    private Long idPerson;
    private String idTeacher;
}
