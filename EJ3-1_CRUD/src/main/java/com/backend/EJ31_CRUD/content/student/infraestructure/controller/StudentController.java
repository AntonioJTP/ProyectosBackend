package com.backend.EJ31_CRUD.content.student.infraestructure.controller;

import com.backend.EJ31_CRUD.content.person.application.errorHandler.CustomError;
import com.backend.EJ31_CRUD.content.person.application.errorHandler.exceptions.NotFoundException;
import com.backend.EJ31_CRUD.content.person.application.services.PersonService;
import com.backend.EJ31_CRUD.content.person.domain.Person;
import com.backend.EJ31_CRUD.content.student.application.services.StudentService;
import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.input.StudentInputDTO;
import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.output.FullStudentOutputDTO;
import com.backend.EJ31_CRUD.content.student.infraestructure.controller.dto.output.StudentOutputDTO;
import com.backend.EJ31_CRUD.content.studentSubject.application.services.StudentSubjectService;
import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.studentSubject.infraestructure.controller.dto.output.StudentSubjectOutputDTO;
import com.backend.EJ31_CRUD.content.teacher.application.services.TeacherService;
import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    PersonService personService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentSubjectService studentSubjectService;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentInputDTO studentInputDTO) {
        Optional<Person> oPerson;

        Optional<Teacher> oTeacher;
        try {
            oTeacher = teacherService.findById(studentInputDTO.getIdTeacher());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID de profesor no valida");
        }

        try {
            oPerson = personService.findById(studentInputDTO.getIdPerson());

            if (oPerson.get().getStudent() != null || oPerson.get().getTeacher() != null)
                return ResponseEntity.badRequest().body("Esta persona ya se encuentra asignada");
        } catch (NotFoundException n) {
            return ResponseEntity.status(404).body(new CustomError(new Date(), 404, n.getMessage()));
        }

        Student student = new Student(studentInputDTO, oPerson.get(), oTeacher.get());

        try {
            studentService.createStudent(student);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No es posible insertar un profesor como nulo");
        }

        StudentOutputDTO studentOutputDTO = new StudentOutputDTO(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentOutputDTO);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<?> addSubjectsByStudent(@PathVariable String id, @RequestBody List<String> idsSubject) {
        List<StudentSubjectOutputDTO> outputDTO = new ArrayList<>();
        List<StudentSubject> returnedList;

        try {
            returnedList = studentSubjectService.addSubjectsById(id, idsSubject);
        } catch (NullPointerException n) {
            return ResponseEntity.badRequest().body("ID de asignatura no valida");
        } catch (IllegalArgumentException i) {
            return ResponseEntity.badRequest().body("No es posible insertar una relacion entre estudiante y asignatura repetida");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID de estudiante no valida");
        }

        returnedList.forEach(key -> outputDTO.add(new StudentSubjectOutputDTO(key)));
        return ResponseEntity.ok(outputDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllStudent() {
        List<StudentOutputDTO> outputStudentList = new ArrayList();

        try {
            studentService.findAll().forEach(key -> outputStudentList.add(new StudentOutputDTO(key)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No hay estudiantes registrados");
        }

        return ResponseEntity.ok(outputStudentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById (@PathVariable String id, @RequestParam(defaultValue = "simple", name = "outputType") String outputType) {
        Optional<Student> oStudent;

        try {
            oStudent = studentService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        if (Objects.equals(outputType, "simple")) {
            StudentOutputDTO simple = new StudentOutputDTO(oStudent.get());
            return ResponseEntity.ok(simple);
        }

        if (Objects.equals(outputType, "full")) {
            FullStudentOutputDTO full = new FullStudentOutputDTO(oStudent.get());
            return ResponseEntity.ok(full);
        }

        return ResponseEntity.badRequest().body(outputType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentById(@PathVariable String id, @RequestBody StudentInputDTO studentInputDTO) {
        Student studentOutput;

        try {
            studentOutput = studentService.updateStudent(id, studentInputDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        StudentOutputDTO studentOutputDTO = new StudentOutputDTO(studentOutput);

        return ResponseEntity.ok(studentOutputDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable String id) {
        Optional<Student> oStudent;

        try {
            oStudent = studentService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        StudentOutputDTO studentOutputDTO = new StudentOutputDTO(oStudent.get());

        studentService.deleteById(id);
        return ResponseEntity.ok(studentOutputDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubjectsByStudent(@PathVariable String id, @RequestBody List<String> idsSubject) {
        List<StudentSubjectOutputDTO> outputDTO = new ArrayList<>();
        List<StudentSubject> returnedList;

        try {
            returnedList = studentSubjectService.deleteSubjectsById(id, idsSubject);
        } catch (NullPointerException n) {
            return ResponseEntity.badRequest().body("ID de asignatura no valida");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID de estudiante no valida");
        }

        returnedList.forEach(key -> outputDTO.add(new StudentSubjectOutputDTO(key)));
        return ResponseEntity.ok(outputDTO);
    }
}
