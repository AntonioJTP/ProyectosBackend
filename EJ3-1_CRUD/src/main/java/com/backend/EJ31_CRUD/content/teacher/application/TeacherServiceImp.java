package com.backend.EJ31_CRUD.content.teacher.application;

import com.backend.EJ31_CRUD.content.teacher.application.services.TeacherService;
import com.backend.EJ31_CRUD.content.teacher.domain.Teacher;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.controller.dto.input.TeacherInputDTO;
import com.backend.EJ31_CRUD.content.teacher.infraestructure.repository.services.TeacherRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherServiceImp implements TeacherService {
    @Autowired
    TeacherRepositoryService teacherRepositoryService;

    @Override
    public void createTeacher(Teacher teacher) throws Exception {
        if (teacher == null) throw new Exception();
        teacherRepositoryService.save(teacher);
    }

    @Override
    public Optional<Teacher> findById(String id) throws Exception {
        if (teacherRepositoryService.findById(id).isEmpty()) throw new Exception();
        return teacherRepositoryService.findById(id);
    }

    @Override
    public List<Teacher> findAll() throws Exception {
        if (teacherRepositoryService.findAll().size() == 0) throw new Exception();
        return teacherRepositoryService.findAll();
    }

    @Override
    public Teacher updateTeacher(String id, TeacherInputDTO teacher) throws Exception {
        Optional<Teacher> mainTeacher = teacherRepositoryService.findById(id);
        if (mainTeacher.isEmpty()) throw new Exception();
        if (!Objects.equals(teacher.getBranch(), null)) mainTeacher.get().setBranch(teacher.getBranch());
        if (!Objects.equals(teacher.getComents(), null)) mainTeacher.get().setComents(teacher.getComents());

        return teacherRepositoryService.save(mainTeacher.get());
    }

    @Override
    public void deleteById(String id) throws Exception {
        if (teacherRepositoryService.findById(id).get().getStudents() == null) throw new Exception();
        teacherRepositoryService.delete(id);
    }
}
