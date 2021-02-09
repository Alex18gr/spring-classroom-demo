package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.entity.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {

    public List<ClassroomDto> findAllClassrooms();

    public Classroom saveClassroom(Classroom classroom);

    public void deleteClassroom(Classroom classroom);

    public void deleteClassroomById(int id);

    public Optional<Classroom> findById(Integer id);

}
