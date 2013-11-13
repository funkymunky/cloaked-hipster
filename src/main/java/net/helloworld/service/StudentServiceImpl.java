package net.helloworld.service;

import net.helloworld.dao.StudentDao;
import net.helloworld.model.Address;
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
        return studentDao.getAddressForStudent(id);
    }

    @Override
    public void updateAddressForStudent(int id, Address address) {
        Student student = getStudent(id);
        studentDao.updateAddress(student, address);
    }
}
