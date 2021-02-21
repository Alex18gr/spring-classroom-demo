package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.dto.StudentDto;
import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import io.alexc.classroomdemo.error.ClassroomNotFoundException;
import io.alexc.classroomdemo.mapper.ClassroomMapper;
import io.alexc.classroomdemo.mapper.StudentMapper;
import io.alexc.classroomdemo.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public List<ClassroomDto> findAllClassrooms() {
        return this.classroomRepository.findAll().stream()
                .map(this::convertClassroomToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassroomDto saveClassroom(ClassroomDto classroomDto) {
        Classroom classroom = ClassroomMapper.getInstance().toEntity(classroomDto);
        return ClassroomMapper.getInstance().toDto(this.classroomRepository.save(classroom));
    }

    @Override
    public ClassroomDto updateClassroom(Integer id, ClassroomDto classroomDto) {
        return this.classroomRepository.findById(id)
                .map(c -> {
                    c.setName(classroomDto.getName());
                    c.setStudents(classroomDto.getStudents().stream().map(this::convertStudentToEntity).collect(Collectors.toList()));
                    c.setId(id);
                    return this.convertClassroomToDto(this.classroomRepository.save(c));
                })
                .orElseThrow(() -> new ClassroomNotFoundException(id));
    }

    @Override
    public void deleteClassroom(ClassroomDto classroomDto) {
        Classroom classroomToDelete = this.classroomRepository.findById(classroomDto.getId()).orElseThrow(() -> new ClassroomNotFoundException(classroomDto.getId()));
        this.classroomRepository.delete(classroomToDelete);
    }

    @Override
    public void deleteClassroomById(int id) {
        if (this.classroomRepository.existsById(id)) {
            this.classroomRepository.deleteById(id);
        } else {
            throw new ClassroomNotFoundException();
        }
    }

    @Override
    public ClassroomDto getClassroom(Integer id) {
        return ClassroomMapper.getInstance().toDto(this.classroomRepository.findById(id)
                .orElseThrow(() -> new ClassroomNotFoundException(id)));
    }

    private ClassroomDto convertClassroomToDto(Classroom classroom) {
        return ClassroomMapper.getInstance().toDto(classroom);
    }


    private Student convertStudentToEntity(StudentDto studentDto) {
        return StudentMapper.getInstance().toEntity(studentDto);
    }
}
