package com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.output.StudentWithoutSubjectOutputDTO;
import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SubjectOutputDTO {
    private String idSubject;
    private String name;
    private String coments;
    private Set<StudentWithoutSubjectOutputDTO> students;

    public SubjectOutputDTO(Subject subject) {
        setIdSubject(subject.getIdSubject());
        setName(subject.getName());
        setComents(subject.getComents());

        if (subject.getStudents() != null) {
            Set<StudentWithoutSubjectOutputDTO> studentSet = new HashSet<>();
            for (StudentSubject studentSubject : subject.getStudents()) {
                StudentWithoutSubjectOutputDTO outputDTO = new StudentWithoutSubjectOutputDTO(studentSubject);
                studentSet.add(outputDTO);
            }
            setStudents(studentSet);
        }
    }
}
