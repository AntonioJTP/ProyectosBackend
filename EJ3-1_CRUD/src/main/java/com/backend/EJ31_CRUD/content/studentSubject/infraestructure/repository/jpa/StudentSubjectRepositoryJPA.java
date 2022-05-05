package com.backend.EJ31_CRUD.content.studentSubject.infraestructure.repository.jpa;

import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRepositoryJPA extends JpaRepository<StudentSubject, String> {
}
