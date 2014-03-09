package net.helloworld.dao;

import net.helloworld.model.Address;
import net.helloworld.model.Education;
import net.helloworld.model.Sponsorship;
import net.helloworld.model.Student;

import java.util.List;

/**
 * Date: 17/10/13
 * Time: 11:10 PM
 */
public interface StudentDao {

    public void addStudent(Student student);
    public void updateStudent(Student student);
    public Student getStudent(int id);
    public List<Student> getAllStudents();

    public Address getAddressForStudent(int id);
    public void updateAddress(Student student, Address address);

    public Education getEducationForStudent(int id);
    public void updateEducation(Student student, Education education);

    public Sponsorship getSponsorshipForStudent(int id);
    public void updateSponsorshipForStudent(Student student, Sponsorship sponsorship);

}
