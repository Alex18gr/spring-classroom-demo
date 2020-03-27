package io.alexc.classroomdemo.repository;

import io.alexc.classroomdemo.entity.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("StudentRepository Integration Tests")
public class StudentRepositoryIntegrationTest {

    private final String STUDENT_FIRST_NAME = "A student name";
    private final String STUDENT_LAST_NAME = "A student last name";
    private final Double STUDENT_GRADE = 15.5;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void whenFindById_thenReturnStudent() {
        Student s = new Student();
        s.setFirstName(STUDENT_FIRST_NAME);
        s.setLastName(STUDENT_LAST_NAME);
        s.setClassroom(null);
        s.setBirthDate(new Date());
        s.setGrade(STUDENT_GRADE);

        Student savedStudent = entityManager.persist(s);
        entityManager.flush();

        Optional<Student> foundStudent = studentRepository.findById(savedStudent.getId());

        assertThat(foundStudent.isPresent()).isEqualTo(true);

        assertThat(foundStudent.get().getFirstName()).isEqualTo(STUDENT_FIRST_NAME);
    }

}
