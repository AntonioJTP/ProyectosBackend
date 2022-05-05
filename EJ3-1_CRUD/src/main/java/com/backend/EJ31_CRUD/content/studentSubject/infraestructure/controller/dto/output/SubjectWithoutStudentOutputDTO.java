package com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.output;

import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.output.SimpleSubjectOutputDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectWithoutStudentOutputDTO {
    private SimpleSubjectOutputDTO subject;

    public SubjectWithoutStudentOutputDTO (StudentSubject studentSubject) {
        setSubject(new SimpleSubjectOutputDTO(studentSubject.getSubject()));
    }
}
