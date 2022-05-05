package com.backend.EJ31_CRUD.content.studentSubject.domain;

import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.input.StudentSubjectInputDTO;
import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import com.backend.EJ31_CRUD.sequences.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSubject_seq")
    @GenericGenerator(
            name = "studentSubject_seq",
            strategy = "com.backend.EJ31_CRUD.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SS"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")

            })
    @Column(name = "ID_STUDENT_SUBJECT")
    private String idStudentSubject;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "ID_STUDENT")
    private Student student;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "ID_SUBJECT")
    private Subject subject;

    @NonNull
    @Column(name = "INITIAL_DATE")
    private Date initialDate;

    @Column(name = "FINISH_DATE")
    private Date finishDate;

    public StudentSubject(StudentSubjectInputDTO studentSubjectInputDTO, Student student, Subject subject) {
        setIdStudentSubject(studentSubjectInputDTO.getIdStudentSubject());
        setStudent(student);
        setSubject(subject);
        setInitialDate(studentSubjectInputDTO.getInitialDate());
        setFinishDate(studentSubjectInputDTO.getFinishDate());
    }

    public StudentSubject(Student student, Subject subject) {
        setStudent(student);
        setSubject(subject);
        setInitialDate(new Date());
    }
}
