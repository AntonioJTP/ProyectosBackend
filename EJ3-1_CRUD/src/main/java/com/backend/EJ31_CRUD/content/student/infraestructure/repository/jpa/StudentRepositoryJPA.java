package com.backend.EJ31_CRUD.content.student.infraestructure.repository.jpa;

import com.backend.EJ31_CRUD.content.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryJPA extends JpaRepository<Student, String> {
}
