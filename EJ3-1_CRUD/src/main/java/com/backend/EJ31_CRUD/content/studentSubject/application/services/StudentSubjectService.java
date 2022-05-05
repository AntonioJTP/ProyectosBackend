package com.backend.EJ31_CRUD.content.studentSubject.application.services;

import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import java.util.List;
import java.util.Optional;

public interface StudentSubjectService {
    void createStudentSubject(StudentSubject studentSubject) throws Exception;
    List<StudentSubject> addSubjectsById(String idStudent, List<String> idsSubjects) throws Exception;
    Optional<StudentSubject> findById(String id) throws Exception;
    List<StudentSubject> findAll() throws Exception;
    void deleteById(String id);
    List<StudentSubject> deleteSubjectsById(String idStudent, List<String> idsSubjects) throws Exception; // ?¿
}
