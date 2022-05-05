package com.backend.EJ31_CRUD.content.student.domain;

import com.backend.EJ31_CRUD.content.person.domain.Person;
import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import com.backend.EJ31_CRUD.sequences.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @GenericGenerator(
            name = "student_seq",
            strategy = "com.backend.EJ31_CRUD.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ST"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")

            })
    @Column(name = "ID_STUDENT")
    private String idStudent;

    @NonNull
    @Column(name = "NUM_HOURS_WEEK")
    private Integer hoursWeek;

    @NonNull
    @Column(name = "BRANCH")
    private String branch;

    @Column(name = "COMENTS")
    private String coments;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_PERSON")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TEACHER")
    @JsonBackReference
    private Teacher teacher;

    @OneToMany(mappedBy = "student", cascade = CascadeType.DETACH)
    private Set<StudentSubject> studies;

    public Student (StudentInputDTO studentInputDTO, Person person, Teacher teacher) {
        setIdStudent(studentInputDTO.getIdStudent());
        setHoursWeek(studentInputDTO.getHoursWeek());
        setBranch(studentInputDTO.getBranch());
        setComents(studentInputDTO.getComents());
        setPerson(person);
        setTeacher(teacher);
    }
}
