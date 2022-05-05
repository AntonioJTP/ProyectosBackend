package com.backend.EJ31_CRUD.content.subject.domain;

import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.input.SubjectInputDTO;
import com.backend.EJ31_CRUD.sequences.StringPrefixedSequenceIdGenerator;
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
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_seq")
    @GenericGenerator(
            name = "subject_seq",
            strategy = "com.backend.EJ31_CRUD.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SU"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")

            })
    @Column(name = "ID_SUBJECT")
    private String idSubject;

    @NonNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "COMENTS")
    private String coments;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.DETACH)
    private Set<StudentSubject> students;

    public Subject (SubjectInputDTO subjectInputDTO) {
        setIdSubject(subjectInputDTO.getIdSubject());
        setName(subjectInputDTO.getName());
        setComents(subjectInputDTO.getComents());
    }
}
