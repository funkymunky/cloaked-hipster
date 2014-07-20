package net.helloworld.dao.impl;

import net.helloworld.dao.FeesDao;
import net.helloworld.model.Fees;
import net.helloworld.model.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class FeesDaoImpl implements FeesDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Fees getCurrentFeeIssueDate() {
        List feeList = sessionFactory.getCurrentSession().createQuery("from Fees order by issueDate desc").list();
        return feeList.size() == 0 ? new Fees() : (Fees) feeList.get(0);

    }

    @Override
    public void setFeeIssueDate(Fees fees) {
        getCurrentSession().save(fees);
    }


    private BigDecimal getMonthlyAllowanceFor(Long student) {
        Query query = sessionFactory.getCurrentSession().createQuery("Select s from Student as s right outer join s.education as e");
        List<Student> list = query.list();

        List<Student> selectedStudent = list.stream()
                .filter(s -> s.getEducation() != null &&
                        s.getId().equals(student))
                .collect(Collectors.toList());


//        String sql = String.format("Select e from Students s join Education e on e.id = s.education_id where s.id = %s", student.toString());
//        Query query = sessionFactory.getCurrentSession().createQuery(sql);
//        List<Education> list = query.list();
        if (selectedStudent.size() > 0) {
            return selectedStudent.get(0).getEducation().getMonthlyAllowance();
        }
        return BigDecimal.valueOf(0);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
