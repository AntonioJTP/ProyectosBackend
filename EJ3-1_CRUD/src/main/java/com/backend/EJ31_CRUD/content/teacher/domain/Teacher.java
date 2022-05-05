package com.backend.EJ31_CRUD.content.teacher.domain;

import com.backend.EJ31_CRUD.content.person.domain.Person;
import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.input.TeacherInputDTO;
import com.backend.EJ31_CRUD.sequences.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    @GenericGenerator(
            name = "teacher_seq",
            strategy = "com.backend.EJ31_CRUD.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TE"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")

            })
    @Column(name = "ID_TEACHER")
    private String idTeacher;

    @NonNull
    @Column(name = "BRANCH")
    private String branch;

    @Column(name = "COMENTS")
    private String coments;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_PERSON")
    private Person person;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Student> students;

    public Teacher (TeacherInputDTO teacherInputDTO, Person person) {
        setIdTeacher(teacherInputDTO.getIdTeacher());
        setBranch(teacherInputDTO.getBranch());
        setComents(teacherInputDTO.getComents());
        setPerson(person);
    }
}
