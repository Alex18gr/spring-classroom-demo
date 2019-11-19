package io.alexc.classroomdemo.repository;

import io.alexc.classroomdemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Override
    <S extends Student> S save(S s);

    @Override
    void delete(Student student);

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<Student> findById(Integer integer);
}
