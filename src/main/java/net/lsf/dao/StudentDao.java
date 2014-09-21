package net.lsf.dao;

import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.*;

import java.util.List;
import java.util.Set;

/**
 * Date: 17/10/13
 * Time: 11:10 PM
 */
public interface StudentDao {

    void addStudent(Student student);
    void updateStudent(Student student);
    Student getStudent(int id);
    List<Student> getAllStudents();
    Set<Student> getStudentByNameOrStandingOrder(String searchText);

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

    List<String> getAllProfilePictureNames();

    List<Student> getAllStudentsBySponsorshipType(SponsorshipType sponsorshipType);

    List<Student> getAllStudentsByInstitutionType(SponsorshipType sponsorshipType, InstitutionType institutionType);

    List<Student> getAllStudentsWithBank(SponsorshipType sponsorshipType, String bankName);

    void updateCommentsForStudent(Student student, Comments comments);
}
