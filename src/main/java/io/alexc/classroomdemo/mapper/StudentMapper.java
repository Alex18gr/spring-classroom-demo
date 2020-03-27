package io.alexc.classroomdemo.mapper;

import io.alexc.classroomdemo.dto.ClassroomDto;
import io.alexc.classroomdemo.dto.StudentDto;
import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.entity.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentMapper {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static StudentMapper instance;

    private StudentMapper() { }

    public static StudentMapper getInstance() {
        if (instance == null) instance = new StudentMapper();
        return instance;
    }

    public StudentDto toDto(Student s) {
        StudentDto dto = new StudentDto();
        dto.setId(s.getId());
        dto.setFirstName(s.getFirstName());
        dto.setLastName(s.getLastName());
        dto.setBirthDate(this.dateFormat.format(s.getBirthDate()));
        dto.setGrade(s.getGrade());
        return dto;
    }

    public List<StudentDto> toDtoList(Collection<Student> students) {
        List<StudentDto> dtos = new ArrayList<>(students.size());
        students.forEach((s) -> dtos.add(toDto(s)));
        return dtos;
    }

    public Student toEntity(StudentDto dto) {
        Student s = new Student();
        s.setId(dto.getId());
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setGrade(dto.getGrade());
        try {
            s.setBirthDate(this.dateFormat.parse(dto.getBirthDate()));
        } catch (ParseException e) {
            s.setBirthDate(null);
            e.printStackTrace();
        }
        return s;
    }



}
