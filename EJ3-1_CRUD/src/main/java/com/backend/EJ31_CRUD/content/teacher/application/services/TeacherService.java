package com.backend.EJ31_CRUD.content.teacher.application.services;

import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.input.TeacherInputDTO;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    void createTeacher(Teacher teacher) throws Exception;
    Optional<Teacher> findById(String id) throws Exception;
    List<Teacher> findAll() throws Exception;
    Teacher updateTeacher(String id, TeacherInputDTO teacher) throws Exception;
    void deleteById(String id) throws Exception;
}
