package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StudentController {

    public List<Student> getStudents();

    public Student getStudent(@PathVariable Integer id);

    public Classroom getStudentsClassrooms(@PathVariable Integer id);

}
