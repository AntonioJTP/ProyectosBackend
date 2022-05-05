package com.backend.EJ31_CRUD.content.student.infraestructure.repository.services;

import com.backend.EJ31_CRUD.content.student.domain.Student;
import java.util.List;
import java.util.Optional;

public interface StudentRepositoryService {
    Student save(Student student);
    Optional<Student> findById(String id);
    List<Student> findAll();
    void delete(String id);
}
