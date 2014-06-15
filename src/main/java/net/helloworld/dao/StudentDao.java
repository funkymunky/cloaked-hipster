package net.helloworld.dao;

import net.helloworld.model.*;

import java.util.List;

/**
 * Date: 17/10/13
 * Time: 11:10 PM
 */
public interface StudentDao {

    void addStudent(Student student);
    void updateStudent(Student student);
    Student getStudent(int id);
    List<Student> getAllStudents();
    List<Student> getStudentByNameOrStandingOrder(String searchText);

    Address getAddressForStudent(int id);
    void updateAddress(Student student, Address address);

    Education getEducationForStudent(int id);
    void updateEducation(Student student, Education education);

    Sponsorship getSponsorshipForStudent(int id);
    void updateSponsorshipForStudent(Student student, Sponsorship sponsorship);

    Bank getBankDetailsForStudent(int id);
    void updateBankDetailsForStudent(Student student, Bank bank);

    String getProfilePicForStudent(int id);
    void updateProfilePicForStudent(Student student, String profilePic);
}
