package io.alexc.classroomdemo.service;

import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.repository.ClassroomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClassroomServiceImplIntegrationTest {

    @TestConfiguration
    static class ClassroomServiceImplTestContextConfiguration {

        @Autowired
        private ClassroomRepository classroomRepository;

        @Bean
        public ClassroomService classroomService() {
            return new ClassroomServiceImpl(classroomRepository);
        }

    }

    @MockBean
    private ClassroomRepository classroomRepository;

    @Autowired
    private ClassroomService classroomService;

    @BeforeEach
    public void setUp() {
        Classroom classroom = new Classroom();
        classroom.setName("test 1");
        classroom.setId(555);
        classroom.setStudents(new ArrayList<>());

        Mockito.when(classroomRepository.findById(555))
                .thenReturn(java.util.Optional.of(classroom));
    }

    @Test
    public void whenValidId_ClassroomShouldBeFound() {
        Integer id = 555;

        Optional<Classroom> foundClassroom = classroomService.findById(555);

        assertThat(foundClassroom.isPresent()).isEqualTo(true);

        assertThat(foundClassroom.get().getName()).isEqualTo("test 1");
    }

}
