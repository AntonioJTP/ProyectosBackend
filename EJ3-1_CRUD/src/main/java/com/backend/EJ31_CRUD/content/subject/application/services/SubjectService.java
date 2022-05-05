package com.backend.EJ31_CRUD.content.subject.application.services;

import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.input.SubjectInputDTO;
import java.util.List;
import java.util.Optional;

public interface SubjectService {
    void createSubject(Subject subject) throws Exception;
    Optional<Subject> findById(String id) throws Exception;
    List<Subject> findAll() throws Exception;
    Subject updateSubject(String id, SubjectInputDTO subject) throws Exception;
    void deleteById(String id) throws Exception;
}
