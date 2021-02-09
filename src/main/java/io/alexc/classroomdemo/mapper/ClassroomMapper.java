package io.alexc.classroomdemo.mapper;

import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.dto.StudentDto;
import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;
import org.aspectj.weaver.patterns.IScope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClassroomMapper {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static ClassroomMapper instance;

    private ClassroomMapper() { }

    public static ClassroomMapper getInstance() {
        if (instance == null) instance = new ClassroomMapper();
        return instance;
    }

    public ClassroomDto toDto(Classroom c) {
        ClassroomDto dto = new ClassroomDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setStudents(StudentMapper.getInstance().toDtoList(c.getStudents()));
        c.getStudents().forEach((stud) -> {
            dto.getStudents().add(StudentMapper.getInstance().toDto(stud));
        });
        return dto;
    }

    public List<ClassroomDto> toDtoList(Collection<Classroom> classrooms) {
        List<ClassroomDto> dtos = new ArrayList<>(classrooms.size());
        classrooms.forEach((c) -> dtos.add(toDto(c)));
        return dtos;
    }

    public Classroom toEntity(ClassroomDto dto) {
        Classroom c = new Classroom();
        c.setId(dto.getId());
        c.setName(dto.getName());
        c.setStudents(new ArrayList<>());
        dto.getStudents().forEach((studentDto) -> {
            c.getStudents().add(StudentMapper.getInstance().toEntity(studentDto));
        });
        return c;

    }



}
