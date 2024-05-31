package com.ira.spring.boot.docker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ira.spring.boot.docker.pojo.Student;
import com.ira.spring.boot.docker.util.StudentUtility;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    private List<Student> studentList;

    @BeforeEach
    public void setUp() {
        student = new Student("1", "John Doe", "2D");
        studentList = Arrays.asList(student);
    }

    @Test
    public void testGetAllStudents() {
        try (MockedStatic<StudentUtility> studentUtilityMockedStatic = mockStatic(StudentUtility.class)) {
            studentUtilityMockedStatic.when(StudentUtility::getAll).thenReturn(studentList);

            List<Student> result = studentService.getAllStudents();
            assertEquals(1, result.size());
            studentUtilityMockedStatic.verify(StudentUtility::getAll);
        }
    }

    @Test
    public void testGetStudent() {
        try (MockedStatic<StudentUtility> studentUtilityMockedStatic = mockStatic(StudentUtility.class)) {
            studentUtilityMockedStatic.when(() -> StudentUtility.get("1")).thenReturn(student);

            Student result = studentService.getStudent("1");
            assertEquals("John Doe", result.getStudentName());
            studentUtilityMockedStatic.verify(() -> StudentUtility.get("1"));
        }
    }

    @Test
    public void testUpdateStudent() {
        try (MockedStatic<StudentUtility> studentUtilityMockedStatic = mockStatic(StudentUtility.class)) {
            studentUtilityMockedStatic.when(() -> StudentUtility.update("1", student)).thenReturn(student);

            Student result = studentService.updateStudent("1", student);
            assertEquals("John Doe", result.getStudentName());
            studentUtilityMockedStatic.verify(() -> StudentUtility.update("1", student));
        }
    }

    @Test
    public void testCreateStudent() {
        try (MockedStatic<StudentUtility> studentUtilityMockedStatic = mockStatic(StudentUtility.class)) {
            studentUtilityMockedStatic.when(() -> StudentUtility.create(student)).thenReturn(studentList);

            List<Student> result = studentService.createStudent(student);
            assertEquals(1, result.size());
            studentUtilityMockedStatic.verify(() -> StudentUtility.create(student));
        }
    }

    @Test
    public void testDeleteStudent() {
        try (MockedStatic<StudentUtility> studentUtilityMockedStatic = mockStatic(StudentUtility.class)) {
            studentUtilityMockedStatic.when(() -> StudentUtility.delete("1")).thenReturn("Deleted");

            String result = studentService.deleteStudent("1");
            assertEquals("Deleted", result);
            studentUtilityMockedStatic.verify(() -> StudentUtility.delete("1"));
        }
    }
}