package net.helloworld.dao;

import net.helloworld.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
