package net.lsf.dao;

import net.lsf.model.Education;

public interface EducationDao {

    public void addEducation(Education education);
    public void updateEducation(Education education, int id);
    public Education getEducation(int id);
}
