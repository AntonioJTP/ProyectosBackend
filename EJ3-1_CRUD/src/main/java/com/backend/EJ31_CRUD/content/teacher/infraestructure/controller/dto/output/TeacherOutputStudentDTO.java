package com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.output.StudentOutputWithoutTeacherDTO;
import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TeacherOutputStudentDTO {
    private String idTeacher;
    private String branch;
    private String coments;
    private Set<StudentOutputWithoutTeacherDTO> students;

    public TeacherOutputStudentDTO(Teacher teacher) {
        setIdTeacher(teacher.getIdTeacher());
        setBranch(teacher.getBranch());
        setComents(teacher.getComents());

        if (teacher.getStudents() != null) {
            Set<StudentOutputWithoutTeacherDTO> studentSet = new HashSet<>();
            for (Student student : teacher.getStudents()) {
                StudentOutputWithoutTeacherDTO studentOutputDTO = new StudentOutputWithoutTeacherDTO(student);
                studentSet.add(studentOutputDTO);
            }
            setStudents(studentSet);
        }
    }
}
