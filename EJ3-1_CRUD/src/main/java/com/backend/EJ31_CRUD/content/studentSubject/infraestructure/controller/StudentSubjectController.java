package com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller;

import com.backend.EJ31_CRUD.content.student.application.services.StudentService;
import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.output.StudentOutputWithoutTeacherDTO;
import com.backend.EJ31_CRUD.content.studentSubject.application.services.StudentSubjectService;
import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.input.StudentSubjectInputDTO;
import com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.output.StudentSubjectOutputDTO;
import com.backend.EJ31_CRUD.content.subject.application.services.SubjectService;
import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("studentSubject")
public class StudentSubjectController {
    @Autowired
    StudentSubjectService studentSubjectService;

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    @PostMapping
    public ResponseEntity<?> createStudentSubject(@RequestBody StudentSubjectInputDTO studentSubjectInputDTO) {
        Optional<Student> oStudent;
        Optional<Subject> oSubject;

        try {
            oStudent = studentService.findById(studentSubjectInputDTO.getIdStudent());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID de estudiante no valida");
        }

        try {
            oSubject = subjectService.findById(studentSubjectInputDTO.getIdSubject());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID de asignatura no valida");
        }

        StudentSubject studentSubject = new StudentSubject(studentSubjectInputDTO, oStudent.get(), oSubject.get());

        try {
            studentSubjectService.createStudentSubject(studentSubject);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("No es posible insertar una relacion entre estudiante y asignatura repetida");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No es posible insertar la relacion entre estudiante y asignatura como nulo");
        }

        StudentSubjectOutputDTO studentSubjectOutputDTO = new StudentSubjectOutputDTO(studentSubject);
        return ResponseEntity.status(200).body(studentSubjectOutputDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllStudentSubject() {
        List<StudentSubjectOutputDTO> outputDTOList = new ArrayList<>();

        try {
            studentSubjectService.findAll().forEach(key -> outputDTOList.add(new StudentSubjectOutputDTO(key)));
        } catch (Exception e) {
            return ResponseEntity.status(200).body("No hay relaciones entre estudiante y asignatura registradas");
        }

        return ResponseEntity.ok(outputDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectsByStudent(@PathVariable String id) {
        Optional<Student> oStudent;

        try {
            oStudent = studentService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID de estudiante no valida");
        }

        StudentOutputWithoutTeacherDTO outputDTO = new StudentOutputWithoutTeacherDTO(oStudent.get());
        return ResponseEntity.ok(outputDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentSubjectById(@PathVariable String id) {
        Optional<StudentSubject> oStudentSubject;

        try {
            oStudentSubject = studentSubjectService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        StudentSubjectOutputDTO studentSubjectOutputDTO = new StudentSubjectOutputDTO(oStudentSubject.get());

        studentSubjectService.deleteById(id);
        return ResponseEntity.ok(studentSubjectOutputDTO);
    }
}
