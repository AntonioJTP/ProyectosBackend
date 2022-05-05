package com.backend.EJ31_CRUD.content.teacher.infraestructure.repository;

import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.repository.jpa.TeacherRepositoryJPA;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.repository.services.TeacherRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherRepositoryServiceImp implements TeacherRepositoryService {
    @Autowired
    TeacherRepositoryJPA teacherRepositoryJPA;

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepositoryJPA.save(teacher);
    }

    @Override
    public Optional<Teacher> findById(String id) {
        return teacherRepositoryJPA.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepositoryJPA.findAll();
    }

    @Override
    public void delete(String id) {
        teacherRepositoryJPA.deleteById(id);
    }
}
