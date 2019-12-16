package io.alexc.classroomdemo.repository;

import io.alexc.classroomdemo.entity.Classroom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DisplayName("ClassroomRepository Integration Tests")
public class ClassroomRepositoryIntegrationTest {

    private final String CLASSROOM_NAME = "A classroom name";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Test
    @DisplayName("When findById return the classroom with id")
    public void whenFindById_thenReturnClassroom() {
        Classroom c = new Classroom();
        c.setName(CLASSROOM_NAME);
        // Classroom savedClassroom = classroomRepository.save(c);
        Classroom savedClassroom = entityManager.persist(c);
        entityManager.flush();

        Optional<Classroom> foundClassroom =
                classroomRepository.findById(savedClassroom.getId());

        assertThat(foundClassroom.isPresent()).isEqualTo(true);

        assertThat(foundClassroom.get().getName()).isEqualTo(CLASSROOM_NAME);

    }

}
