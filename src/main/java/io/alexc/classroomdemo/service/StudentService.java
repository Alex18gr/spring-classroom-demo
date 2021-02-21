package io.alexc.classroomdemo.service;


import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDto saveStudent(StudentDto student);

    void deleteStudent(StudentDto student);

    void deleteStudent(int studentId);

    StudentDto getStudent(int id);

    StudentDto findStudentByIdAndClassroomId(int classroomId, int id);

    List<StudentDto> getAllStudents();

    ClassroomDto getStudentClassroom(Integer id);

}
