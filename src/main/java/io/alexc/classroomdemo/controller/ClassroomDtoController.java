package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("apiv2/students")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"}, maxAge = 3600)
public class ClassroomDtoController implements ClassroomController {


    @Override
    public List<Classroom> getClassrooms(Pageable pageable) {
        return null;
    }

    @Override
    public Classroom postClassroom(Classroom classroom) {
        return null;
    }

    @Override
    public Classroom getClassroom(Integer id) {
        return null;
    }

    @Override
    public Classroom putClassroom(Classroom classroom, Integer id) {
        return null;
    }

    @Override
    public void deleteClassroom(Integer id) {

    }

    @Override
    public Collection<Student> getClassroomStudents(Integer classroomId) {
        return null;
    }

    @Override
    public Student postClassroomStudent(Integer classroomId, Student student) {
        return null;
    }

    @Override
    public Student getClassroomStudentById(Integer classroomId, Integer studentId) {
        return null;
    }

    @Override
    public Student putClassroomStudent(Integer classroomId, Integer studentId, Student student) {
        return null;
    }

    @Override
    public void deleteClassroomStudent(Integer classroomId, Integer studentId) {

    }
}
