package net.helloworld.service;

import net.helloworld.dao.StudentDao;
import net.helloworld.model.Address;
import net.helloworld.model.Education;
import net.helloworld.model.Sponsorship;
import net.helloworld.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Date: 17/10/13
 * Time: 11:29 PM
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

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
}
