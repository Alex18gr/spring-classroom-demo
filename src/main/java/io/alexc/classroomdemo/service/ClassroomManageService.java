package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.dto.StudentDto;

import java.util.Collection;

public interface ClassroomManageService {

    Collection<StudentDto> getClassroomStudents(Integer classroomId);

    StudentDto saveClassroomStudent(Integer classroomId, StudentDto studentDto);

    StudentDto getClassroomStudent(Integer classroomId, Integer studentId);

    StudentDto updateClassroomStudent(Integer classroomId, Integer studentId, StudentDto studentDto);

    void deleteClassroomStudent(Integer classroomId, Integer studentId);
}
