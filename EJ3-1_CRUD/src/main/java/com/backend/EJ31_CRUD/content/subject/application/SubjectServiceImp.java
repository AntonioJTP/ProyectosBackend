package com.backend.EJ31_CRUD.content.subject.application;

import com.backend.EJ31_CRUD.content.subject.application.services.SubjectService;
import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.input.SubjectInputDTO;
import com.backend.EJ31_CRUD.content.subject.infraestructure.repository.services.SubjectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubjectServiceImp implements SubjectService {
    @Autowired
    SubjectRepositoryService subjectRepositoryService;

    @Override
    public void createSubject(Subject subject) throws Exception {
        if (subject == null) throw new Exception();
        subjectRepositoryService.save(subject);
    }

    @Override
    public Optional<Subject> findById(String id) throws Exception {
        if (subjectRepositoryService.findById(id).isEmpty()) throw new Exception();
        return subjectRepositoryService.findById(id);
    }

    @Override
    public List<Subject> findAll() throws Exception {
        if (subjectRepositoryService.findAll().size() == 0) throw new Exception();
        return subjectRepositoryService.findAll();
    }

    @Override
    public Subject updateSubject(String id, SubjectInputDTO subject) throws Exception {
        Optional<Subject> mainSubject = subjectRepositoryService.findById(id);
        if (mainSubject.isEmpty()) throw new Exception();
        if (!Objects.equals(subject.getName(), null)) mainSubject.get().setName(subject.getName());
        if (!Objects.equals(subject.getComents(), null)) mainSubject.get().setComents(subject.getComents());

        return subjectRepositoryService.save(mainSubject.get());
    }

    @Override
    public void deleteById(String id) throws Exception {
        if (subjectRepositoryService.findById(id).get().getStudents() == null) throw new Exception();
        subjectRepositoryService.delete(id);
    }
}
