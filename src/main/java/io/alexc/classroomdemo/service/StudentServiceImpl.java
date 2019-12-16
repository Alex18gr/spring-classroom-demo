package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.entity.Student;
import io.alexc.classroomdemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Student student) {
        this.studentRepository.delete(student);
    }

    @Override
    public void deleteStudentById(int id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> findStudentById(int id) {
        return this.studentRepository.findById(id);
    }

    @Override
    public Optional<Student> findStudentByIdAndClassroomId(int classroomId, int id) {
        return this.studentRepository.findByClassroom_IdAndId(classroomId, id);
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }
}
