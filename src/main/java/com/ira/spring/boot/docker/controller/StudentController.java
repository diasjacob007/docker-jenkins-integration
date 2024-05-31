/**
 * 
 */
package com.ira.spring.boot.docker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ira.spring.boot.docker.pojo.Student;
import com.ira.spring.boot.docker.service.StudentService;

import lombok.NonNull;

/**
 * 
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable @NonNull String studentId) {
        return studentService.getStudent(studentId);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable @NonNull String studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }

    @PostMapping
    public List<Student> createStudent(@RequestBody @NonNull Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable @NonNull String studentId) {
        return studentService.deleteStudent(studentId);
    }
}
