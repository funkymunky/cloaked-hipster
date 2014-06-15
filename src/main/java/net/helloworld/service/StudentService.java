package net.helloworld.service;

import net.helloworld.model.*;

import java.util.List;

/**
 * Date: 17/10/13
 * Time: 11:28 PM
 */
public interface StudentService {

    void addStudent(Student student);
    void updateStudent(Student student);
    Student getStudent(int id);
    List<Student> getAllStudents();
    List<Student> getStudentByNameOrStandingOrder(String searchText);

    Address getAddressForStudent(int id);
    void updateAddressForStudent(int id, Address address);

    Education getEducationForStudent(int id);
    void updateEducationForStudent(int id, Education education);

    Sponsorship getSponsorshipForStudent(int id);
    void updateSponsorshipForStudent(int id, Sponsorship sponsorship);

    Bank getBankForStudent(int id);
    void updateBankForStudent(int id, Bank bank);

    String getProfilePicForStudent(int id);
    void updateProfilePicForStudent(int id, String profilePic);
}
