package com.backend.EJ31_CRUD.content.studentSubject.infraestructure.repository;

import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.studentSubject.infraestructure.repository.jpa.StudentSubjectRepositoryJPA;
import com.backend.EJ31_CRUD.content.studentSubject.infraestructure.repository.services.StudentSubjectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentSubjectRepositoryServiceImp implements StudentSubjectRepositoryService {
    @Autowired
    StudentSubjectRepositoryJPA studentSubjectRepositoryJPA;

    @Override
    public StudentSubject save(StudentSubject studentSubject) {
        return studentSubjectRepositoryJPA.save(studentSubject);
    }

    @Override
    public Optional<StudentSubject> findById(String id) {
        return studentSubjectRepositoryJPA.findById(id);
    }

    @Override
    public List<StudentSubject> findAll() {
        return studentSubjectRepositoryJPA.findAll();
    }

    @Override
    public void delete(String id) {
        studentSubjectRepositoryJPA.deleteById(id);
    }
}
