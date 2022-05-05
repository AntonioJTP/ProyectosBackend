package com.backend.EJ31_CRUD.content.subject.infraestructure.repository;

import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import com.backend.EJ31_CRUD.content.subject.infraestructure.repository.jpa.SubjectRepositoryJPA;
import com.backend.EJ31_CRUD.content.subject.infraestructure.repository.services.SubjectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectRepositoryServiceImp implements SubjectRepositoryService {
    @Autowired
    SubjectRepositoryJPA subjectRepositoryJPA;

    @Override
    public Subject save(Subject subject) {
        return subjectRepositoryJPA.save(subject);
    }

    @Override
    public Optional<Subject> findById(String id) {
        return subjectRepositoryJPA.findById(id);
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepositoryJPA.findAll();
    }

    @Override
    public void delete(String id) {
        subjectRepositoryJPA.deleteById(id);
    }
}
