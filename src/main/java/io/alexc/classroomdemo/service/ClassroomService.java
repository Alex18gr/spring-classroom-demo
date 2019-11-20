package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> findAllClassrooms() {
        return this.classroomRepository.findAll();
    }

    public Classroom saveClassroom(Classroom classroom) {
        return this.classroomRepository.save(classroom);
    }

    public void deleteClassroom(Classroom classroom) {
        this.classroomRepository.delete(classroom);
    }

    public void deleteClassroomById(int id) {
        this.classroomRepository.deleteById(id);
    }

    public Optional<Classroom> findById(Integer id) {
        return this.classroomRepository.findById(id);
    }
}
