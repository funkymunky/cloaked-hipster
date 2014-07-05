package net.helloworld.dao.impl;

import net.helloworld.InstitutionType;
import net.helloworld.dao.EducationDao;
import net.helloworld.model.Education;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EducationDaoImpl implements EducationDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addEducation(Education education) {
        getCurrentSession().save(education);
    }

    @Override
    public void updateEducation(Education education, int id) {
        Education educationToUpdate = getEducation(id);
        updateEducationDetails(education, educationToUpdate);
    }

    @Override
    public Education getEducation(int id) {
        Education education = (Education) getCurrentSession().get(Education.class, id);
        return education;
    }

    private void updateEducationDetails(Education education, Education educationToUpdate) {
        educationToUpdate.setInstitutionType(education.getInstitutionType());
        educationToUpdate.setInstitutionName(education.getInstitutionName());
        if (!educationToUpdate.getInstitutionType().equals(InstitutionType.School)) {
            educationToUpdate.setDegreeName(education.getDegreeName());
        }
        educationToUpdate.setYearOfStudy(education.getYearOfStudy());
        educationToUpdate.setMonthlyAllowance(education.getMonthlyAllowance());
        educationToUpdate.setApplicationDate(education.getApplicationDate());
        educationToUpdate.setStartDate(education.getStartDate());
        educationToUpdate.setEndDate(education.getEndDate());
        educationToUpdate.setAgent(education.getAgent());
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
