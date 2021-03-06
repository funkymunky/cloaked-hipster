package net.lsf.service.impl;

import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.dao.StudentDao;
import net.lsf.dao.StudentSponsorFeesDao;
import net.lsf.data.StudentFeeDTO;
import net.lsf.model.*;
import net.lsf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * Date: 17/10/13
 * Time: 11:29 PM
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentSponsorFeesDao studentSponsorFeesDao;

    @Value("${max.upload.size}")
    private String maxUploadSize;

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public Student getStudent(int id) {
        return studentDao.getStudent(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public Set<Student> getStudentByNameOrStandingOrderOrAccountNumber(String searchText) {
        return studentDao.getStudentByNameOrStandingOrderOrAccountNumber(searchText);
    }

    @Override
    public Address getAddressForStudent(int id) {
        Address address = studentDao.getAddressForStudent(id);
        return address == null ? null : address;
    }

    @Override
    public void updateAddressForStudent(int id, Address address) {
        Student student = getStudent(id);
        studentDao.updateAddress(student, address);
    }

    @Override
    public Education getEducationForStudent(int id) {
        Education education = studentDao.getEducationForStudent(id);
        return education == null ? null : education;
    }

    @Override
    public void updateEducationForStudent(int id, Education education) {
        Student student = getStudent(id);
        studentDao.updateEducation(student, education);
    }

    @Override
    public Sponsorship getSponsorshipForStudent(int id) {
        Sponsorship sponsorship = studentDao.getSponsorshipForStudent(id);
        return sponsorship == null ? null : sponsorship;
    }

    @Override
    public void updateSponsorshipForStudent(int id, Sponsorship sponsorship) {
        Student student = getStudent(id);
        studentDao.updateSponsorshipForStudent(student, sponsorship);
    }

    @Override
    public Bank getBankForStudent(int id) {
        Bank bank = studentDao.getBankDetailsForStudent(id);
        return bank == null ? null : bank;
    }

    @Override
    public void updateBankForStudent(int id, Bank bank) {
        Student student = getStudent(id);
        studentDao.updateBankDetailsForStudent(student, bank);
    }

    @Override
    public String getProfilePicForStudent(int id) {
        String profilePic = studentDao.getProfilePicForStudent(id);
        return profilePic == null ? "default_profile_icon.gif" : profilePic;
    }

    @Override
    public void updateProfilePicForStudent(int id, String profilePic) {
        Student student = getStudent(id);
        studentDao.updateProfilePicForStudent(student, profilePic);
    }

    @Override
    public List<String> getAllProfilePics() {
        return studentDao.getAllProfilePictureNames();
    }

    @Override
    public List<Student> getAllStudentsByInstitutionType(SponsorshipType sponsorshipType, InstitutionType institutionType) {
        return studentDao.getAllStudentsByInstitutionType(sponsorshipType, institutionType);
    }

    @Override
    public List<Student> getAllStudentsWithBank(SponsorshipType sponsorshipType, String bankName) {
        return studentDao.getAllStudentsWithBank(sponsorshipType, bankName);
    }

    @Override
    public void updateCommentsForStudent(int id, Comments comments) {
        Student student = studentDao.getStudent(id);
        studentDao.updateCommentsForStudent(student, comments);
    }

    @Override
    public String getApplicationDateForStudent(int id) {
        return studentDao.getApplicationDateForStudent(id);
    }

    @Override
    public String getMaxUploadFileSize() {
        return maxUploadSize;
    }

    @Override
    public StudentFeeDTO populateStudentFeeInfo(int studentId) {
        return studentSponsorFeesDao.getStudentSponsorFeeInformation(studentId);
    }

    @Override
    public void updateStudentSponsorFees(StudentFeeDTO studentSponsorFee, int studentId) throws ParseException {
        studentSponsorFeesDao.updateFeesForStudentAndSponsor(studentSponsorFee, studentId);
    }

    @Override
    public StudentFeeDTO getStudentSponsorFees(int studentId) {
        return studentSponsorFeesDao.getStudentSponsorFeeInformation(studentId);
    }
}
