package com.backend.EJ31_CRUD.content.studentSubject.application;

import com.backend.EJ31_CRUD.content.student.domain.Student;
import com.backend.EJ31_CRUD.content.student.infraestructure.repository.services.StudentRepositoryService;
import com.backend.EJ31_CRUD.content.studentSubject.application.services.StudentSubjectService;
import com.backend.EJ31_CRUD.content.studentSubject.domain.StudentSubject;
import com.backend.EJ31_CRUD.content.studentSubject.infraestructure.repository.services.StudentSubjectRepositoryService;
import com.backend.EJ31_CRUD.content.subject.domain.Subject;
import com.backend.EJ31_CRUD.content.subject.infraestructure.repository.services.SubjectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentSubjectServiceImp implements StudentSubjectService {
    @Autowired
    StudentSubjectRepositoryService studentSubjectRepositoryService;

    @Autowired
    StudentRepositoryService studentRepositoryService;

    @Autowired
    SubjectRepositoryService subjectRepositoryService;

    @Override
    public void createStudentSubject(StudentSubject studentSubject) throws Exception {
        if (studentSubject == null) throw new Exception();

        if (studentSubjectRepositoryService.findAll().size() != 0) {
            studentSubjectRepositoryService.findAll().forEach(key -> {
                if (Objects.equals(key.getStudent().getIdStudent(), studentSubject.getStudent().getIdStudent())
                        && Objects.equals(key.getSubject().getIdSubject(), studentSubject.getSubject().getIdSubject())) throw new IllegalArgumentException();
            });
        }

        studentSubjectRepositoryService.save(studentSubject);
    }

    @Override
    public List<StudentSubject> addSubjectsById(String idStudent, List<String> idsSubjects) throws Exception {
        List<StudentSubject> studentSubjectList = new ArrayList<>();
        Optional<Student> oStudent = studentRepositoryService.findById(idStudent);
        if (oStudent.isEmpty()) throw new Exception();

        idsSubjects.forEach(key -> {
            Optional<Subject> oSubject = subjectRepositoryService.findById(key);
            if (oSubject.isEmpty()) throw new NullPointerException();

            StudentSubject studentSubject = new StudentSubject(oStudent.get(), oSubject.get());
            studentSubjectList.add(studentSubject);

            if (studentSubjectRepositoryService.findAll().size() != 0) {
                studentSubjectRepositoryService.findAll().forEach(pointer -> {
                    if (Objects.equals(pointer.getStudent().getIdStudent(), studentSubject.getStudent().getIdStudent())
                            && Objects.equals(pointer.getSubject().getIdSubject(), studentSubject.getSubject().getIdSubject())) throw new IllegalArgumentException();
                });
            }

            studentSubjectRepositoryService.save(studentSubject);
        });

        return studentSubjectList;
    }

    @Override
    public Optional<StudentSubject> findById(String id) throws Exception {
        if (studentSubjectRepositoryService.findById(id).isEmpty()) throw new Exception();
        return studentSubjectRepositoryService.findById(id);
    }

    @Override
    public List<StudentSubject> findAll() throws Exception {
        if (studentSubjectRepositoryService.findAll().size() == 0) throw new Exception();
        return studentSubjectRepositoryService.findAll();
    }

    @Override
    public void deleteById(String id) {
        studentSubjectRepositoryService.delete(id);
    }

    @Override
    public List<StudentSubject> deleteSubjectsById(String idStudent, List<String> idsSubjects) throws Exception {
        List<StudentSubject> studentSubjectList = new ArrayList<>();
        Optional<Student> oStudent = studentRepositoryService.findById(idStudent);
        if (oStudent.isEmpty()) throw new Exception();

        idsSubjects.forEach(key -> {
            Optional<Subject> oSubject = subjectRepositoryService.findById(key);
            if (oSubject.isEmpty()) throw new NullPointerException();

            StudentSubject studentSubject = new StudentSubject(oStudent.get(), oSubject.get());
            studentSubjectList.add(studentSubject);

            if (studentSubjectRepositoryService.findAll().size() != 0) {
                studentSubjectRepositoryService.findAll().forEach(pointer -> {
                    if (Objects.equals(pointer.getStudent().getIdStudent(), studentSubject.getStudent().getIdStudent())
                            && Objects.equals(pointer.getSubject().getIdSubject(), studentSubject.getSubject().getIdSubject()))
                        studentSubjectRepositoryService.delete(pointer.getIdStudentSubject());
                });
            }
        });

        return studentSubjectList;
    }
}
