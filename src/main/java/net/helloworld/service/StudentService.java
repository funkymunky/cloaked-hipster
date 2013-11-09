package net.helloworld.service;

import net.helloworld.model.Student;

import java.util.List;

/**
 * Date: 17/10/13
 * Time: 11:28 PM
 */
public interface StudentService {

    public void addStudent(Student student);
    public void updateStudent(Student student);
    public Student getStudent(int id);
    public List<Student> getAllStudents();
}
