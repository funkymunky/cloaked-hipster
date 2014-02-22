package net.helloworld.dao;

import net.helloworld.model.Education;

public interface EducationDao {

    public void addEducation(Education education);
    public void updateEducation(Education education, int id);
    public Education getEducation(int id);
}
