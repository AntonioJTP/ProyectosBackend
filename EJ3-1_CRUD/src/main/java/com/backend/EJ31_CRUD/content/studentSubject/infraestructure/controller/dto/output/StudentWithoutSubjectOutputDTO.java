package com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.output.StudentOutputWithoutTeacherAndSubjectDTO;
import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentWithoutSubjectOutputDTO {
    private StudentOutputWithoutTeacherAndSubjectDTO student;

    public StudentWithoutSubjectOutputDTO (StudentSubject studentSubject) {
        setStudent(new StudentOutputWithoutTeacherAndSubjectDTO(studentSubject.getStudent()));
    }
}
