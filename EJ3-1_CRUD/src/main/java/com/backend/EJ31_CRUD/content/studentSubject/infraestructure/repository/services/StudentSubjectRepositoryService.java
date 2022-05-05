package com.backend.EJ31_CRUD.content.studentSubject.infraestructure.repository.services;

import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import java.util.List;
import java.util.Optional;

public interface StudentSubjectRepositoryService {
    StudentSubject save(StudentSubject studentSubject);
    Optional<StudentSubject> findById(String id);
    List<StudentSubject> findAll();
    void delete(String id);
}
