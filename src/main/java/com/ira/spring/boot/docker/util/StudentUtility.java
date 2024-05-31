/**
 * 
 */
package com.ira.spring.boot.docker.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ira.spring.boot.docker.exception.StudentAlreadyExistsException;
import com.ira.spring.boot.docker.exception.StudentNotFoundException;
import com.ira.spring.boot.docker.pojo.Student;

/**
 * 
 */
public class StudentUtility {

    private static List<Student> studentsList = new ArrayList<>();

    public static List<Student> create(Student student) {
        if (studentsList.isEmpty()) {
            studentsList.add(student);
        } else {
            Optional<Student> studentInstance = studentsList.stream()
                    .filter(stud -> stud.getStudentId().equals(student.getStudentId())).findFirst();
            if (studentInstance.isPresent()) {
                var message = "Student already exists with this same id: " + student.getStudentId();
                throw new StudentAlreadyExistsException(message);
            }
            studentsList.add(student);
        }
        return studentsList;
    }

    public static List<Student> getAll() {
        return studentsList;
    }

    public static Student get(String studId) {
        Optional<Student> optionalStudent = studentsList.stream()
                .filter(student -> student.getStudentId().equals(studId)).findAny();
        if (optionalStudent.isPresent())
            return optionalStudent.get();
        else
            throw new StudentNotFoundException("No student exists with this Id: " + studId);
    }

    public static Student update(String studentId, Student student) {
        Optional<Student> optionalStudent = studentsList.stream().filter(stud -> stud.getStudentId().equals(studentId))
                .findAny();
        if (optionalStudent.isPresent()) {
            studentsList.remove(optionalStudent.get());
            studentsList.add(student);
            return studentsList.stream().filter(stu -> stu.getStudentId().equals(studentId)).findFirst().get();
        } else
            throw new StudentNotFoundException("No student exists with this Id: " + studentId);
    }

    public static String delete(String studId) {
        Optional<Student> optionalStudent = studentsList.stream()
                .filter(student -> student.getStudentId().equals(studId)).findAny();
        if (optionalStudent.isPresent()) {
            studentsList.remove(optionalStudent.get());
            return "Student with Id: " + studId + " deleted successfully...";
        } else
            throw new StudentNotFoundException("No student exists with this Id: " + studId);
    }
}
