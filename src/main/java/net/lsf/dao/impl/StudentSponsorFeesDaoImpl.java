package net.lsf.dao.impl;

import net.lsf.dao.StudentDao;
import net.lsf.dao.StudentSponsorFeesDao;
import net.lsf.data.StudentFeeDTO;
import net.lsf.model.Education;
import net.lsf.model.Sponsorship;
import net.lsf.model.Student;
import net.lsf.model.StudentSponsorFees;
import net.lsf.utils.Formatter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

@Repository
@Transactional
public class StudentSponsorFeesDaoImpl implements StudentSponsorFeesDao {

    private final Formatter formatter = new Formatter();

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private StudentDao studentDao;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public StudentFeeDTO getStudentSponsorFeeInformation(int studentId) {
        StudentFeeDTO studentFeeDto = new StudentFeeDTO();
        Sponsorship sponsorship = getSponsorshipForStudent(studentId);
        Education education = getEducationForStudent(studentId);

        studentFeeDto.setMonthlyAllowance(education.getMonthlyAllowance().toString());
        studentFeeDto.setElectedCurrency(sponsorship.getElectedCurrency());
        studentFeeDto.setPaymentFrom(sponsorship.getPaymentFrom());
        studentFeeDto.setPaymentTo(sponsorship.getPaymentTill());

        StudentSponsorFees currentStudentSponsorFees = getStudentSponsorFeeForStudent(studentId);
        if (currentStudentSponsorFees != null) {
            studentFeeDto.setBankFee(currentStudentSponsorFees.getBankFee().toString());
            studentFeeDto.setExchangeRate(currentStudentSponsorFees.getExchangeRate().toString());
            studentFeeDto.setAmountOutstanding(currentStudentSponsorFees.getAmountToPay().toString());
        }

        return studentFeeDto;
    }


    @Override
    public void updateFeesForStudentAndSponsor(StudentFeeDTO studentFeeDTO, int studentId) throws ParseException {
        Student student = getStudent(studentId);
        Long sponsor_id = student.getSponsorship().getSponsor();

        StudentSponsorFees studentSponsorFees = new StudentSponsorFees();
        studentSponsorFees.setStudent(Long.valueOf(student.getId()));
        studentSponsorFees.setSponsor(sponsor_id);
        studentSponsorFees.setBankFee(convertStringToDecimal(studentFeeDTO.getBankFee()));
        studentSponsorFees.setExchangeRate(convertStringToDecimal(studentFeeDTO.getExchangeRate()));
        studentSponsorFees.setAmountToPay(convertStringToDecimal(studentFeeDTO.getAmountOutstanding()));

        StudentSponsorFees currentStudentSponsorFees = getStudentSponsorFeeForStudent(studentId);
        if (currentStudentSponsorFees == null) {
            getCurrentSession().save(studentSponsorFees);
        } else {
            currentStudentSponsorFees.setBankFee(studentSponsorFees.getBankFee());
            currentStudentSponsorFees.setExchangeRate(studentSponsorFees.getExchangeRate());
            currentStudentSponsorFees.setAmountToPay(studentSponsorFees.getAmountToPay());
            getCurrentSession().update(currentStudentSponsorFees);
        }
    }

    private Student getStudent(int studentId) {
        return studentDao.getStudent(studentId);
    }

    private Sponsorship getSponsorshipForStudent(int studentId) {
        return studentDao.getStudent(studentId).getSponsorship();
    }

    private Education getEducationForStudent(int studentId) {
        return studentDao.getStudent(studentId).getEducation();
    }

    private StudentSponsorFees getStudentSponsorFeeForStudent(int studentId) {
        List resultSetRows = sessionFactory.getCurrentSession().createQuery("from StudentSponsorFees where student_id = " + studentId + " order by id desc").list();
        return resultSetRows.size() == 0 ? null : (StudentSponsorFees) resultSetRows.get(0);
    }

    private BigDecimal convertStringToDecimal(String stringToConvert) throws ParseException {
        DecimalFormat decimalFormat = formatter.getDecimalFormat();

        BigDecimal bigDecimal = stringToConvert.equals("") ? BigDecimal.ZERO : (BigDecimal) decimalFormat.parse(stringToConvert);
        return bigDecimal;
    }
}