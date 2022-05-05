package com.backend.EJ31_CRUD.content.student.infraestructure.repository;

import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.student.infraestructure.repository.jpa.StudentRepositoryJPA;
import com.backend.EJ31_CRUD.content.student.infraestructure.repository.services.StudentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentRepositoryServiceImp implements StudentRepositoryService {
    @Autowired
    StudentRepositoryJPA studentRepositoryJPA;

    @Override
    public Student save(Student student) {
        return studentRepositoryJPA.save(student);
    }

    @Override
    public Optional<Student> findById(String id) {
        return studentRepositoryJPA.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepositoryJPA.findAll();
    }

    @Override
    public void delete(String id) {
        studentRepositoryJPA.deleteById(id);
    }
}
