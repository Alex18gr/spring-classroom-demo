package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.dto.StudentDto;
import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import io.alexc.classroomdemo.error.ClassroomNotFoundException;
import io.alexc.classroomdemo.error.StudentNotFoundException;
import io.alexc.classroomdemo.mapper.ClassroomMapper;
import io.alexc.classroomdemo.mapper.StudentMapper;
import io.alexc.classroomdemo.repository.ClassroomRepository;
import io.alexc.classroomdemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ClassroomManageServiceImpl implements ClassroomManageService {

    private final ClassroomService classroomService;

    private final StudentService studentService;

    private final ClassroomRepository classroomRepository;

    private final StudentRepository studentRepository;

    public ClassroomManageServiceImpl(ClassroomService classroomService, StudentService studentService, ClassroomRepository classroomRepository, StudentRepository studentRepository) {
        this.classroomService = classroomService;
        this.studentService = studentService;
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Collection<StudentDto> getClassroomStudents(Integer classroomId) {
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        return classroom.getStudents().stream()
                .map(this::convertStudentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto saveClassroomStudent(Integer classroomId, StudentDto studentDto) {
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        Student student = StudentMapper.getInstance().toEntity(studentDto);
        student.setClassroom(classroom);
        return StudentMapper.getInstance().toDto(this.studentRepository.save(student));
    }

    @Override
    public StudentDto getClassroomStudent(Integer classroomId, Integer studentId) {
        return StudentMapper.getInstance().toDto(this.studentRepository.findByClassroom_IdAndId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
    }

    @Override
    public StudentDto updateClassroomStudent(Integer classroomId, Integer studentId, StudentDto studentDto) {
        Classroom classroom = this.classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId));
        Student student = StudentMapper.getInstance().toEntity(studentDto);
        student.setClassroom(classroom);
        return StudentMapper.getInstance().toDto(this.studentRepository.findById(studentId)
                .map(s -> {
                    s.setClassroom(classroom);
                    s.setBirthDate(student.getBirthDate());
                    s.setFirstName(student.getFirstName());
                    s.setLastName(student.getLastName());
                    s.setGrade(student.getGrade());
                    return this.studentRepository.save(s);
                })
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
    }

    @Override
    public void deleteClassroomStudent(Integer classroomId, Integer studentId) {
        this.studentRepository.delete(this.studentRepository.findByClassroom_IdAndId(classroomId, studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId, classroomId)));
    }

    private Classroom convertClassroomToEntity(ClassroomDto classroomDto) {
        return ClassroomMapper.getInstance().toEntity(classroomDto);
    }

    private StudentDto convertStudentToDto(Student student) {
        return StudentMapper.getInstance().toDto(student);
    }
}
