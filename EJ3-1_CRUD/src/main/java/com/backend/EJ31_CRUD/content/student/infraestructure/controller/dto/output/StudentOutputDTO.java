package com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.output.SubjectWithoutStudentOutputDTO;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.output.TeacherOutputWithoutStudentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class StudentOutputDTO {
    private String idStudent;
    private Integer hoursWeek;
    private String branch;
    private String coments;
    private TeacherOutputWithoutStudentDTO teacher;
    private Set<SubjectWithoutStudentOutputDTO> subjects;

    public StudentOutputDTO(Student student) {
        setIdStudent(student.getIdStudent());
        setHoursWeek(student.getHoursWeek());
        setBranch(student.getBranch());
        setComents(student.getComents());
        setTeacher(new TeacherOutputWithoutStudentDTO(student.getTeacher()));

        if (student.getStudies() != null) {
            Set<SubjectWithoutStudentOutputDTO> subjectSet = new HashSet<>();
            for (StudentSubject studentSubject : student.getStudies()) {
                SubjectWithoutStudentOutputDTO outputDTO = new SubjectWithoutStudentOutputDTO(studentSubject);
                subjectSet.add(outputDTO);
            }
            setSubjects(subjectSet);
        }
    }
}
