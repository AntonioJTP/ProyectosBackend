package com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.output.StudentOutputWithoutTeacherAndSubjectDTO;
import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.output.SimpleSubjectOutputDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class StudentSubjectOutputDTO {
    private String idStudentSubject;
    private StudentOutputWithoutTeacherAndSubjectDTO student;
    private SimpleSubjectOutputDTO subject;
    private Date initialDate;
    private Date finishDate;

    public StudentSubjectOutputDTO (StudentSubject studentSubject) {
        setIdStudentSubject(studentSubject.getIdStudentSubject());
        setStudent(new StudentOutputWithoutTeacherAndSubjectDTO(studentSubject.getStudent()));
        setSubject(new SimpleSubjectOutputDTO(studentSubject.getSubject()));
        setInitialDate(studentSubject.getInitialDate());
        setFinishDate(studentSubject.getFinishDate());
    }
}
