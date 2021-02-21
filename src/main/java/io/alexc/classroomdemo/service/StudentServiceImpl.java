package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.dto.StudentDto;
import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import io.alexc.classroomdemo.error.StudentNotFoundException;
import io.alexc.classroomdemo.mapper.ClassroomMapper;
import io.alexc.classroomdemo.mapper.StudentMapper;
import io.alexc.classroomdemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = this.convertStudentToEntity(studentDto);
        return this.convertStudentToDto(this.studentRepository.save(student));
    }

    @Override
    public void deleteStudent(StudentDto studentDto) {
        Student student = this.studentRepository.findById(studentDto.getId()).orElseThrow(() -> new StudentNotFoundException(studentDto.getId()));
        this.studentRepository.delete(student);
    }

    @Override
    public void deleteStudent(int id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public StudentDto getStudent(int id) {
        return StudentMapper.getInstance().toDto(this.studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id)));
    }

    @Override
    public StudentDto findStudentByIdAndClassroomId(int classroomId, int studentId) {
        Student student = this.studentRepository.findByClassroom_IdAndId(classroomId, studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        return this.convertStudentToDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return this.studentRepository.findAll().stream().map(this::convertStudentToDto).collect(Collectors.toList());
    }

    @Override
    public ClassroomDto getStudentClassroom(Integer id) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return ClassroomMapper.getInstance().toDto(student.getClassroom());
    }

    private Classroom convertClassroomToEntity(ClassroomDto classroomDto) {
        return ClassroomMapper.getInstance().toEntity(classroomDto);
    }

    private StudentDto convertStudentToDto(Student student) {
        return StudentMapper.getInstance().toDto(student);
    }
    private Student convertStudentToEntity(StudentDto studentDto) {
        return StudentMapper.getInstance().toEntity(studentDto);
    }


}
