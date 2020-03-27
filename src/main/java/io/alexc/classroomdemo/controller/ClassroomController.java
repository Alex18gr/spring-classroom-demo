package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

public interface ClassroomController {

    public List<Classroom> getClassrooms(Pageable pageable);

    public Classroom postClassroom(@RequestBody Classroom classroom);

    public Classroom getClassroom(@PathVariable Integer id);

    public Classroom putClassroom(@RequestBody Classroom classroom, @PathVariable Integer id);

    public void deleteClassroom(@PathVariable Integer id);

    public Collection<Student> getClassroomStudents(@PathVariable Integer classroomId);

    public Student postClassroomStudent(@PathVariable Integer classroomId, @RequestBody Student student);

    public Student getClassroomStudentById(@PathVariable Integer classroomId, @PathVariable Integer studentId);

    public Student putClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId, @RequestBody Student student);

    public void deleteClassroomStudent(@PathVariable Integer classroomId, @PathVariable Integer studentId);

}
