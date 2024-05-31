package com.ira.spring.boot.docker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ira.spring.boot.docker.pojo.Student;
import com.ira.spring.boot.docker.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;
    private List<Student> studentList;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        student = new Student("1", "John Doe", "2D");
        studentList = Arrays.asList(student);
    }

    @Test
    public void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(studentList);

        mockMvc.perform(get("/student/all")).andExpect(status().isOk()).andExpect(jsonPath("$[0].studentId").value("1"))
                .andExpect(jsonPath("$[0].studentName").value("John Doe"))
                .andExpect(jsonPath("$[0].studentClass").value("2D"));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    public void testGetStudent() throws Exception {
        when(studentService.getStudent("1")).thenReturn(student);

        mockMvc.perform(get("/student/1")).andExpect(status().isOk()).andExpect(jsonPath("$.studentId").value("1"));

        verify(studentService, times(1)).getStudent("1");
    }

    @Test
    public void testUpdateStudent() throws Exception {
        when(studentService.updateStudent(eq("1"), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/student/1").contentType("application/json")
                .content("{\"studentId\":\"1\",\"studentName\":\"John Doe\",\"studentClass\":\"2D\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.studentId").value("1"));

        verify(studentService, times(1)).updateStudent(eq("1"), any(Student.class));
    }

    @Test
    public void testCreateStudent() throws Exception {
        when(studentService.createStudent(any(Student.class))).thenReturn(studentList);

        mockMvc.perform(post("/student").contentType("application/json")
                .content("{\"studentId\":\"1\",\"studentName\":\"John Doe\",\"studentClass\":\"2D\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].studentId").value("1"))
                .andExpect(jsonPath("$[0].studentName").value("John Doe"))
                .andExpect(jsonPath("$[0].studentClass").value("2D"));

        verify(studentService, times(1)).createStudent(any(Student.class));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        when(studentService.deleteStudent("1")).thenReturn("Deleted");

        mockMvc.perform(delete("/student/1")).andExpect(status().isOk()).andExpect(content().string("Deleted"));

        verify(studentService, times(1)).deleteStudent("1");
    }
}
