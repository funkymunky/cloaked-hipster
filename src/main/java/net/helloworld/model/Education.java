package net.helloworld.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="EDUCATION")
public class Education implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String institutionType;
    private String institutionName;
    private String degreeName;
    private int yearOfStudy;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date applicationDate;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date startDate;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date endDate;
    private BigDecimal monthlyAllowance;
    private String agent;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMonthlyAllowance() {
        return monthlyAllowance;
    }

    public void setMonthlyAllowance(BigDecimal monthlyAllowance) {
        this.monthlyAllowance = monthlyAllowance;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return String.format("name of institution: %s", this.institutionName);
    }
}
