package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.error.ClassroomNotFoundException;
import io.alexc.classroomdemo.service.ClassroomService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
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

}
