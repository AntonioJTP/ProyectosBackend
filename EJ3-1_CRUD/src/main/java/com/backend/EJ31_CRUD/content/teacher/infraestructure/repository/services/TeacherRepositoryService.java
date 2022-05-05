package com.backend.EJ31_CRUD.content.teacher.infraestructure.repository.services;

import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import java.util.List;
import java.util.Optional;

public interface TeacherRepositoryService {
    Teacher save(Teacher teacher);
    Optional<Teacher> findById(String id);
    List<Teacher> findAll();
    void delete(String id);
}
