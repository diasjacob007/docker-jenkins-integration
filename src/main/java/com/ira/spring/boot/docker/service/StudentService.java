/**
 * 
 */
package com.ira.spring.boot.docker.service;

import java.util.List;

import com.ira.spring.boot.docker.pojo.Student;

/**
 * 
 */
public interface StudentService {

    List<Student> createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudent(String studentId);

    Student updateStudent(String studentId, Student student);

    String deleteStudent(String studentId);
}
