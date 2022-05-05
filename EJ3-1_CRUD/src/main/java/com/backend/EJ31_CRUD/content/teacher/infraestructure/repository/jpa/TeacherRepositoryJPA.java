package com.backend.EJ31_CRUD.content.teacher.infraestructure.repository.jpa;

import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepositoryJPA extends JpaRepository<Teacher, String> {
}
