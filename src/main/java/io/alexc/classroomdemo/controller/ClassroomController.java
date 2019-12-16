package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import io.alexc.classroomdemo.error.ClassroomNotFoundException;
import io.alexc.classroomdemo.error.StudentNotFoundException;
import io.alexc.classroomdemo.service.ClassroomService;
import io.alexc.classroomdemo.service.ClassroomServiceImpl;
import io.alexc.classroomdemo.service.StudentService;
import io.alexc.classroomdemo.service.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("classrooms")
// For fixing the CORS issues due to different domain with the front-end
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ClassroomController {

    private final ClassroomService classroomService;

    private final StudentService studentService;

    public ClassroomController(ClassroomService classroomService, StudentService studentService) {
        this.classroomService = classroomService;
        this.studentService = studentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Classroom> getClassrooms() {
        return classroomService.findAllClassrooms();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Classroom postClassroom(
            @RequestBody Classroom classroom
    ) {
        return this.classroomService.saveClassroom(classroom);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Classroom getClassroom(@PathVariable Integer id) {
        return this.classroomService.findById(id)
                .orElseThrow(() -> new ClassroomNotFoundException(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Classroom putClassroom(
            @RequestBody Classroom classroom,
            @PathVariable Integer id) {
        return classroomService.findById(id)
                .map(c -> {
                    c.setName(classroom.getName());
                    c.setStudents(classroom.getStudents());
                    return this.classroomService.saveClassroom(classroom);
                })
                .orElseThrow(() -> new ClassroomNotFoundException(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteClassroom(
            @PathVariable Integer id
    ) {
        this.classroomService.deleteClassroomById(id);
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.GET)
    public Collection<Student> getClassroomStudents(@PathVariable Integer classroomId) {
        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        return classroom.getStudents();
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.POST)
    public Student postClassroomStudent(
            @PathVariable Integer classroomId,
            @RequestBody Student student
    ) {
        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        student.setClassroom(classroom);
        return this.studentService.save(student);
    }
    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.GET)
    public Student getClassroomStudentById(
            @PathVariable Integer classroomId,
            @PathVariable Integer studentId
    ) {
        return this.studentService.findStudentByIdAndClassroomId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId));
    }
    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.PUT)
    public Student putClassroomStudent(
            @PathVariable Integer classroomId,
            @PathVariable Integer studentId,
            @RequestBody Student student
    ) {
        Classroom classroom = this.classroomService.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        student.setClassroom(classroom);
        return this.studentService.findStudentById(studentId)
                .map(s -> {
                    s.setClassroom(classroom);
                    s.setBirthDate(student.getBirthDate());
                    s.setFirstName(student.getFirstName());
                    s.setLastName(student.getLastName());
                    s.setGrade(student.getGrade());
                    return this.studentService.save(s);
                })
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId));
    }
    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.DELETE)
    public void deleteClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId) {
        this.studentService.deleteStudent(this.studentService.findStudentByIdAndClassroomId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
    }

}
