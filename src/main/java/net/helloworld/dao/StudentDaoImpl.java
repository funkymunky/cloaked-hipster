package net.helloworld.dao;

import net.helloworld.model.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;
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
        if (student.getProfilePic() == null) {
            student.setProfilePic("default_profile_icon.gif");
        }
        return student;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> getAllStudents() {
        return sessionFactory.getCurrentSession().createQuery("from Student order by lastname").list();
    }

    @Override
    public List<Student> getStudentByNameOrStandingOrder(String searchText) {
        Query query = sessionFactory.getCurrentSession().createQuery("Select s from Student as s right outer join s.bank as b");
        List list = query.list();


//        Query q = sessionFactory.getCurrentSession().createQuery("select s from Student as s left join fetch s.bank");
//        List list = q.list();

//        Criteria c = sessionFactory.getCurrentSession().createCriteria(Student.class);
//        c.createCriteria("Bank", "bank");
//        c.add(Restrictions.eq("bank.id", "student.bank_id"));
//        c.setFetchMode("bank", FetchMode.JOIN);
//        List list = c.list();

        System.out.println("legnth of list = " + list.size());
        List<Student> studentsWithStandingOrder = list;
        studentsWithStandingOrder.stream()
                .filter(student ->
                        student.getBank() != null &&
                        student.getBank().getStandingOrder().contains(searchText))
                .collect(Collectors.toList());

        System.out.println("number of bank info = " + studentsWithStandingOrder.size());

        List<Student> allStudents = getAllStudents();
        return allStudents.stream()
                .filter(student ->
                        student.getLastName().toLowerCase().contains(searchText.toLowerCase()) ||
                        student.getFirstName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
