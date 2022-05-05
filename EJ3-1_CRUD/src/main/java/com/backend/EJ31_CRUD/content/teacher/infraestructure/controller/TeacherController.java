package com.backend.EJ31_CRUD.content.teacher.infraestructure.controller;

import com.backend.EJ31_CRUD.content.person.application.errorHandler.CustomError;
import com.backend.EJ31_CRUD.content.person.application.errorHandler.exceptions.NotFoundException;
import com.backend.EJ31_CRUD.content.person.application.services.PersonService;
import com.backend.EJ31_CRUD.content.person.domain.Person;
import com.backend.EJ31_CRUD.content.teacher.application.services.TeacherService;
import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.input.TeacherInputDTO;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.output.TeacherOutputDTO;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.output.TeacherOutputStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherInputDTO teacherInputDTO) {
        Optional<Person> oPerson;

        try {
            oPerson = personService.findById(teacherInputDTO.getIdPerson());

            if (oPerson.get().getStudent() != null || oPerson.get().getTeacher() != null)
                return ResponseEntity.badRequest().body("Esta persona ya se encuentra asignada");
        } catch (NotFoundException n) {
            return ResponseEntity.status(404).body(new CustomError(new Date(), 404, n.getMessage()));
        }

        Teacher teacher = new Teacher(teacherInputDTO, oPerson.get());

        try {
            teacherService.createTeacher(teacher);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No es posible insertar un profesor como nulo");
        }

        TeacherOutputDTO teacherOutputDTO = new TeacherOutputDTO(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherOutputDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        List<TeacherOutputStudentDTO> outputTeacherList = new ArrayList();

        try {
            teacherService.findAll().forEach(key -> outputTeacherList.add(new TeacherOutputStudentDTO(key)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No hay profesores registrados");
        }

        return ResponseEntity.ok(outputTeacherList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById (@PathVariable String id) {
        Optional<Teacher> oTeacher;

        try {
            oTeacher = teacherService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        TeacherOutputStudentDTO teacherOutputDTO = new TeacherOutputStudentDTO(oTeacher.get());
        return ResponseEntity.ok(teacherOutputDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacherById(@PathVariable String id, @RequestBody TeacherInputDTO teacherInputDTO) {
        Teacher teacherOutput;

        try {
            teacherOutput = teacherService.updateTeacher(id, teacherInputDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        TeacherOutputDTO teacherOutputDTO = new TeacherOutputDTO(teacherOutput);
        return ResponseEntity.ok(teacherOutputDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable String id) {
        Optional<Teacher> oTeacher;

        try {
            oTeacher = teacherService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        TeacherOutputDTO teacherOutputDTO = new TeacherOutputDTO(oTeacher.get());

        try {
            teacherService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No puedes eliminar un profesor si tiene estudiantes asignados");
        }
        return ResponseEntity.ok(teacherOutputDTO);
    }
}