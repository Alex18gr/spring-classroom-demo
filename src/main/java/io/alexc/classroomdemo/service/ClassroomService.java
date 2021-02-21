package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.dto.ClassroomDto;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {

    List<ClassroomDto> findAllClassrooms();

    ClassroomDto saveClassroom(ClassroomDto classroomDto);

    ClassroomDto updateClassroom(Integer id, ClassroomDto classroomDto);

    void deleteClassroom(ClassroomDto classroomDto);

    void deleteClassroomById(int id);

    ClassroomDto getClassroom(Integer id);

}
