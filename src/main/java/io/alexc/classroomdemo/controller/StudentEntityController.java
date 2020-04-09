package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import io.alexc.classroomdemo.error.StudentNotFoundException;
import io.alexc.classroomdemo.service.StudentService;
import io.alexc.classroomdemo.service.StudentServiceImpl;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"}, maxAge = 3600)
public class StudentEntityController implements StudentController {

    private final StudentService studentService;
    private final SimpMessagingTemplate messagingTemplate;

    public StudentEntityController(StudentService studentService, SimpMessagingTemplate simpMessagingTemplate) {
        this.studentService = studentService;
        this.messagingTemplate = simpMessagingTemplate;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Student> getStudents() {
        this.messagingTemplate.convertAndSend("/chat", "Students Sent to someone...");
        return this.studentService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable Integer id) {
        return this.studentService.findStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @RequestMapping(value = "{id}/classroom", method = RequestMethod.GET)
    public Classroom getStudentsClassrooms(@PathVariable Integer id) {
        Student student = this.studentService.findStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return student.getClassroom();
    }

}
