package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.entity.Student;
import io.alexc.classroomdemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return this.studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        this.studentRepository.delete(student);
    }

    public void deleteStudentById(int id) {
        this.studentRepository.deleteById(id);
    }

    public Optional<Student> findStudentById(int id) {
        return this.studentRepository.findById(id);
    }
}
