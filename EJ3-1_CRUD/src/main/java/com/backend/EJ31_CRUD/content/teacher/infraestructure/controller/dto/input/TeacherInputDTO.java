package com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInputDTO {
    private String idTeacher;
    private String branch;
    private String coments;
    private Long idPerson;
}
