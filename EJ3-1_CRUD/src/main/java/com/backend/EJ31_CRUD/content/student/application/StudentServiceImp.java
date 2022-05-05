package com.backend.EJ31_CRUD.content.student.application;

import com.backend.EJ31_CRUD.content.student.application.services.StudentService;
import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.backend.EJ31_CRUD.content.student.infraestructure.repository.services.StudentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentRepositoryService studentRepositoryService;

    @Override
    public void createStudent(Student student) throws Exception {
        if (student == null) throw new Exception();
        studentRepositoryService.save(student);
    }

    @Override
    public Optional<Student> findById(String id) throws Exception {
        if (studentRepositoryService.findById(id).isEmpty()) throw new Exception();
        return studentRepositoryService.findById(id);
    }

    @Override
    public List<Student> findAll() throws Exception {
        if (studentRepositoryService.findAll().size() == 0) throw new Exception();
        return studentRepositoryService.findAll();
    }

    @Override
    public Student updateStudent(String id, StudentInputDTO student) throws Exception {
        Optional<Student> mainStudent = studentRepositoryService.findById(id);
        if (mainStudent.isEmpty()) throw new Exception();
        if (!Objects.equals(student.getHoursWeek(), null)) mainStudent.get().setHoursWeek(student.getHoursWeek());
        if (!Objects.equals(student.getBranch(), null)) mainStudent.get().setBranch(student.getBranch());
        if (!Objects.equals(student.getComents(), null)) mainStudent.get().setComents(student.getComents());

        return studentRepositoryService.save(mainStudent.get());
    }

    @Override
    public void deleteById(String id) {
        studentRepositoryService.delete(id);
    }
}
