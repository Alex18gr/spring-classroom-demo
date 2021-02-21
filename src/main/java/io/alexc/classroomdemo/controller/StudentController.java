package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.dto.StudentDto;
import io.alexc.classroomdemo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"}, maxAge = 3600)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<StudentDto> getStudents() {
        return this.studentService.getAllStudents();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public StudentDto getStudent(@PathVariable Integer id) {
        return this.studentService.getStudent(id);
    }

    @RequestMapping(value = "{id}/classroom", method = RequestMethod.GET)
    public ClassroomDto getStudentClassroom(@PathVariable Integer id) {
        return this.studentService.getStudentClassroom(id);
    }
}
