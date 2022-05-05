package com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherOutputWithoutStudentDTO {
    private String idTeacher;
    private String branch;
    private String coments;

    public TeacherOutputWithoutStudentDTO(Teacher teacher) {
        setIdTeacher(teacher.getIdTeacher());
        setBranch(teacher.getBranch());
        setComents(teacher.getComents());
    }
}
