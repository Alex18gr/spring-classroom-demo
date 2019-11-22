package io.alexc.classroomdemo.repository;

import io.alexc.classroomdemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Override
    List<Student> findAll();

    @Override
    <S extends Student> S save(S student);

    @Override
    void delete(Student student);

    @Override
    void deleteById(Integer id);

    @Override
    Optional<Student> findById(Integer id);

    Optional<Student> findByClassroom_IdAndId(int classroom_id, int id);
}
