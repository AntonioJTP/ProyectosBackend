package com.backend.EJ31_CRUD.content.subject.infraestructure.repository.services;

import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import java.util.List;
import java.util.Optional;

public interface SubjectRepositoryService {
    Subject save(Subject subject);
    Optional<Subject> findById(String id);
    List<Subject> findAll();
    void delete(String id);
}
