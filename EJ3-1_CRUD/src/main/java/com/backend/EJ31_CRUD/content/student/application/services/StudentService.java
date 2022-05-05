package com.backend.EJ31_CRUD.content.student.application.services;

import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    void createStudent(Student student) throws Exception;
    Optional<Student> findById(String id) throws Exception;
    List<Student> findAll() throws Exception;
    Student updateStudent(String id, StudentInputDTO student) throws Exception;
    void deleteById(String id);
}
