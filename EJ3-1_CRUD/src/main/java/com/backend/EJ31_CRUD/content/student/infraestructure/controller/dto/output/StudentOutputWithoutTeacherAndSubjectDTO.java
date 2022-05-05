package com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.student.domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentOutputWithoutTeacherAndSubjectDTO {
    private String idStudent;
    private Integer hoursWeek;
    private String branch;
    private String coments;

    public StudentOutputWithoutTeacherAndSubjectDTO(Student student) {
        setIdStudent(student.getIdStudent());
        setHoursWeek(student.getHoursWeek());
        setBranch(student.getBranch());
        setComents(student.getComents());
    }
}
