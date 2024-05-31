/**
 * 
 */
package com.ira.spring.boot.docker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ira.spring.boot.docker.pojo.Student;
import com.ira.spring.boot.docker.util.StudentUtility;

/**
 * 
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> getAllStudents() {
        return StudentUtility.getAll();
    }

    @Override
    public Student getStudent(String studentId) {
        return StudentUtility.get(studentId);
    }

    @Override
    public Student updateStudent(String studentId, Student student) {
        return StudentUtility.update(studentId, student);
    }

    @Override
    public List<Student> createStudent(Student student) {
        return StudentUtility.create(student);
    }

    @Override
    public String deleteStudent(String studentId) {
        return StudentUtility.delete(studentId);
    }
}
