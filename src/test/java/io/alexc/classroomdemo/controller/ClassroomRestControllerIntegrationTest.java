package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.entity.Classroom;
import io.alexc.classroomdemo.service.ClassroomService;
import io.alexc.classroomdemo.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassroomEntityController.class)
public class ClassroomRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClassroomService classroomService;

    @MockBean
    private StudentService studentService;

    @Test
    @DisplayName("Given Classrooms, when get classrooms, return Json Array")
    public void givenClassrooms_WhenGetClassrooms_thenReturnJsonArray() throws Exception {
        Classroom classroom1 = new Classroom(501, "Test 1", new ArrayList<>());
        Classroom classroom2 = new Classroom(502, "Test 2", new ArrayList<>());
        Classroom classroom3 = new Classroom(503, "Test 3", new ArrayList<>());

        List<Classroom> allClassrooms = new ArrayList<>();
        allClassrooms.add(classroom1);
        allClassrooms.add(classroom2);
        allClassrooms.add(classroom3);

        given(classroomService.findAllClassrooms()).willReturn(allClassrooms);

        mvc.perform(get("/classrooms/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(classroom1.getName())))
                .andExpect(jsonPath("$[1].name", is(classroom2.getName())))
                .andExpect(jsonPath("$[2].name", is(classroom3.getName())));
    }
}
