package io.alexc.classroomdemo.repository;

import io.alexc.classroomdemo.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

    @Override
    List<Classroom> findAll();

    @Override
    <S extends Classroom> S save(S s);

    @Override
    void delete(Classroom classroom);

    @Override
    void deleteById(Integer integer);
}
