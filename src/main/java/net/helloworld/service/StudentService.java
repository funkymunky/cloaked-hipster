package net.helloworld.service;

import net.helloworld.model.*;

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

    public Address getAddressForStudent(int id);
    public void updateAddressForStudent(int id, Address address);

    public Education getEducationForStudent(int id);
    public void updateEducationForStudent(int id, Education education);

    public Sponsorship getSponsorshipForStudent(int id);
    public void updateSponsorshipForStudent(int id, Sponsorship sponsorship);

    public Bank getBankForStuent(int id);
    public void updateBankForStudent(int id, Bank bank);
}
