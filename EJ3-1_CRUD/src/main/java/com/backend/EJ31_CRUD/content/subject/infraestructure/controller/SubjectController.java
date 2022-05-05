package com.backend.EJ31_CRUD.content.subject.infraestructure.controller;

import com.backend.EJ31_CRUD.content.subject.application.services.SubjectService;
import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.input.SubjectInputDTO;
import com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.output.SimpleSubjectOutputDTO;
import com.backend.EJ31_CRUD.content.subject.infraestructure.controller.dto.output.SubjectOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping
    public ResponseEntity<?> createSubject (@RequestBody SubjectInputDTO subjectInputDTO) {
        Subject subject = new Subject(subjectInputDTO);

        try {
            subjectService.createSubject(subject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No es posible insertar una asignatura como nulo");
        }

        SubjectOutputDTO subjectOutputDTO = new SubjectOutputDTO(subject);
        return ResponseEntity.ok(subjectOutputDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllSubject() {
        List<SubjectOutputDTO> subjectOutputDTO = new ArrayList();

        try {
            subjectService.findAll().forEach(key -> subjectOutputDTO.add(new SubjectOutputDTO(key)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No hay asignaturas registradas");
        }

        return ResponseEntity.ok(subjectOutputDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable String id) {
        Optional<Subject> oSubject;

        try {
            oSubject = subjectService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        SubjectOutputDTO subjectOutputDTO = new SubjectOutputDTO(oSubject.get());
        return ResponseEntity.ok(subjectOutputDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubjectById(@PathVariable String id, @RequestBody SubjectInputDTO subjectInputDTO) {
        Subject subjectOutput;

        try {
            subjectOutput = subjectService.updateSubject(id, subjectInputDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        SubjectOutputDTO subjectOutputDTO = new SubjectOutputDTO(subjectOutput);
        return ResponseEntity.ok(subjectOutputDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubjectById(@PathVariable String id) {
        Optional<Subject> oSubject;

        try {
            oSubject = subjectService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ID no valida");
        }

        SimpleSubjectOutputDTO simpleSubjectOutputDTO = new SimpleSubjectOutputDTO(oSubject.get());

        try {
            subjectService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No puedes eliminar una asignatura si esta ligada a un estudiante");
        }
        return ResponseEntity.ok(simpleSubjectOutputDTO);
    }
}
