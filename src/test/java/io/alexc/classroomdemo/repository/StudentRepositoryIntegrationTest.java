package io.alexc.classroomdemo.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@DisplayName("StudentRepository Integration Tests")
public class StudentRepositoryIntegrationTest {

    private final String STUDENT_NAME = "A classroom name";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;



}
