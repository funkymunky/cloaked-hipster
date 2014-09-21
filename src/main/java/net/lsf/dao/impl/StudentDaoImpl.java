package net.lsf.dao.impl;

import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.dao.StudentDao;
import net.lsf.model.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Date: 17/10/13
 * Time: 11:12 PM
 */
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addStudent(Student student) {
        Sponsorship newSponsorship = new Sponsorship();
        newSponsorship.setSponsorshipType(SponsorshipType.AwaitingSponsorship.getName());
        getCurrentSession().save(newSponsorship);

        student.setSponsorship(newSponsorship);
        getCurrentSession().save(student);
    }

    @Override
    public void updateStudent(Student student) {
        Student studentToUpdate = getStudent(student.getId());
        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setDateOfBirth(student.getDateOfBirth());
        getCurrentSession().update(studentToUpdate);

    }

    @Override
    public void updateAddress(Student student, Address address) {
        Student studentToUpdate = getStudent(student.getId());
        studentToUpdate.setAddress(address);
        getCurrentSession().update(studentToUpdate);
    }

    @Override
    public Student getStudent(int id) {
        Student student = (Student) getCurrentSession().get(Student.class, id);
        return student;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> getAllStudents() {
        return sessionFactory.getCurrentSession().createQuery("from Student order by lastname").list();
    }

    @Override
    public Set<Student> getStudentByNameOrStandingOrder(String searchText) {
        List<Student> studentsMatchingStandingOrder = findStudentsWithStandingOrder(searchText);
        List<Student> studentsMatchingNames = findStudentsWithName(searchText);

        studentsMatchingNames.addAll(studentsMatchingStandingOrder);
        return studentsMatchingNames.stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Address getAddressForStudent(int id) {
        Student currentStudent = getStudent(id);
        return currentStudent.getAddress();
    }

    @Override
    public Education getEducationForStudent(int id) {
        Student currentStudent = getStudent(id);
        return currentStudent.getEducation();
    }

    @Override
    public void updateEducation(Student student, Education education) {
        Student studentToUpdate = getStudent(student.getId());
        studentToUpdate.setEducation(education);
        getCurrentSession().update(studentToUpdate);
    }

    @Override
    public Sponsorship getSponsorshipForStudent(int id) {
        Student currentStudent = getStudent(id);
        return currentStudent.getSponsorship();
    }

    @Override
    public void updateSponsorshipForStudent(Student student, Sponsorship sponsorship) {
        Student studentToUpdate = getStudent(student.getId());
        studentToUpdate.setSponsorship(sponsorship);
        getCurrentSession().update(studentToUpdate);
    }

    @Override
    public Bank getBankDetailsForStudent(int id) {
        Student currentStudent = getStudent(id);
        return currentStudent.getBank();
    }

    @Override
    public void updateBankDetailsForStudent(Student student, Bank bank) {
        Student studentToUpdate = getStudent(student.getId());
        studentToUpdate.setBank(bank);
        getCurrentSession().update(studentToUpdate);
    }

    @Override
    public String getProfilePicForStudent(int id) {
        Student currentStudent = getStudent(id);
        return currentStudent.getProfilePic();
    }

    @Override
    public void updateProfilePicForStudent(Student student, String profilePic) {
        Student studentToUpdate = getStudent(student.getId());
        studentToUpdate.setProfilePic(profilePic);
        getCurrentSession().update(studentToUpdate);
    }

    @Override
    public List<String> getAllProfilePictureNames() {
        List<Student> allStudents = getAllStudents();
        List<String> listOfProfilePictureNames = allStudents.stream()
                .filter(student ->
                        student.getProfilePic() != null)
                .map(Student::getProfilePic)
                .collect(Collectors.toList());

        return listOfProfilePictureNames;
    }

    @Override
    public List<Student> getAllStudentsBySponsorshipType(SponsorshipType sponsorshipType) {
        return sessionFactory.getCurrentSession().createQuery("Select s from Student as s right outer join s.sponsorship as sp where sp.sponsorshipType = '" + sponsorshipType + "'" ).list();
    }

    @Override
    public List<Student> getAllStudentsByInstitutionType(SponsorshipType sponsorshipType, InstitutionType institutionType) {
        List<Student> allStudents = getAllStudentsBySponsorshipType(sponsorshipType);
        List<Student> studentsByInstitutionType = allStudents.stream()
                .filter(student ->
                        student.getEducation() != null &&
                                student.getEducation().getInstitutionType().equals(institutionType.name()))
                .collect(Collectors.toList());

        return studentsByInstitutionType;
    }

    @Override
    public List<Student> getAllStudentsWithBank(SponsorshipType sponsorshipType, String bankName) {
        List<Student> allStudents = getAllStudentsBySponsorshipType(sponsorshipType);
        List<Student> studentsByBank = allStudents.stream()
                .filter(student ->
                        student.getBank() != null &&
                                student.getBank().getBank().equalsIgnoreCase(bankName))
                .collect(Collectors.toList());

        return studentsByBank;
    }

    @Override
    public void updateCommentsForStudent(Student student, Comments comments) {
        Student studentToUpdate = getStudent(student.getId());
        studentToUpdate.setComments(comments);
        getCurrentSession().update(studentToUpdate);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private List<Student> findStudentsWithName(String searchText) {
        return getAllStudents().stream()
                .filter(student ->
                        student.getLastName().toLowerCase().contains(searchText.toLowerCase()) ||
                                student.getFirstName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<Student> findStudentsWithStandingOrder(String searchText) {
        Query query = sessionFactory.getCurrentSession().createQuery("Select s from Student as s right outer join s.bank as b");
        List<Student> list = query.list();

        return list.stream()
                .filter(student ->
                        student.getBank() != null &&
                                student.getBank().getStandingOrder().contains(searchText))
                .collect(Collectors.toList());
    }
}
