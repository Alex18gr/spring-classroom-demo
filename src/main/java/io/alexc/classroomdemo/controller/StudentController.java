package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.entity.Student;
import io.alexc.classroomdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("students")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public List<Student> getStudents() {
//        return this.studentService.find
//    }

}
