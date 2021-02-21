package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.dto.StudentDto;
import io.alexc.classroomdemo.service.ClassroomManageService;
import io.alexc.classroomdemo.service.ClassroomService;
import io.alexc.classroomdemo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("classrooms")
// For fixing the CORS issues due to different domain with the front-end
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"}, maxAge = 3600)
public class ClassroomController {

    private final ClassroomService classroomService;

    private final StudentService studentService;

    private final ClassroomManageService classroomManageService;

    public ClassroomController(ClassroomService classroomService, StudentService studentService, ClassroomManageService classroomManageService) {
        this.classroomService = classroomService;
        this.studentService = studentService;
        this.classroomManageService = classroomManageService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ClassroomDto> getClassrooms() {
        return classroomService.findAllClassrooms();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ClassroomDto createClassroom(@RequestBody ClassroomDto classroomDto) {
        return this.classroomService.saveClassroom(classroomDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ClassroomDto getClassroom(@PathVariable Integer id) {
        return this.classroomService.getClassroom(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ClassroomDto updateClassroom(@RequestBody ClassroomDto classroomDto, @PathVariable Integer id) {
        return this.classroomService.updateClassroom(id, classroomDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteClassroom(@PathVariable Integer id) {
        this.classroomService.deleteClassroomById(id);
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.GET)
    public Collection<StudentDto> getClassroomStudents(@PathVariable Integer classroomId) {
        return this.classroomManageService.getClassroomStudents(classroomId);
    }

    @RequestMapping(value = "/{classroomId}/students", method = RequestMethod.POST)
    public StudentDto saveClassroomStudent(@PathVariable Integer classroomId, @RequestBody StudentDto studentDto) {
        return this.classroomManageService.saveClassroomStudent(classroomId, studentDto);
    }

    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.GET)
    public StudentDto getClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId) {
        return this.classroomManageService.getClassroomStudent(classroomId, studentId);
    }

    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.PUT)
    public StudentDto updateClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId, @RequestBody StudentDto studentDto) {
        return this.classroomManageService.updateClassroomStudent(classroomId, studentId, studentDto);
    }

    @RequestMapping(value = "/{classroomId}/students/{studentId}", method = RequestMethod.DELETE)
    public void deleteClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId) {
        this.classroomManageService.deleteClassroomStudent(classroomId, studentId);
    }



}
