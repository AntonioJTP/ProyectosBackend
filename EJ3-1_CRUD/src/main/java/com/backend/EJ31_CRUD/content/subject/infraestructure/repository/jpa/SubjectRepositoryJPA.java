package com.backend.EJ31_CRUD.content.subject.infraestructure.repository.jpa;

import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepositoryJPA extends JpaRepository<Subject, String> {
}
