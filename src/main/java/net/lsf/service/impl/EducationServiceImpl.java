package net.lsf.service.impl;

import net.lsf.dao.EducationDao;
import net.lsf.model.Education;
import net.lsf.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationDao educationDao;

    @Override
    public void addEducation(Education education) {
        educationDao.addEducation(education);
    }

    @Override
    public void updateEducation(Education education, int id) {
        educationDao.updateEducation(education, id);
    }
}
