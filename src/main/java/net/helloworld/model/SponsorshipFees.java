package net.helloworld.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "SPONSORSHIPFEES")
public class SponsorshipFees implements Serializable {

    private static final long serialVersionUID = 1L;

    public SponsorshipFees(Date feeIssueDate, BigDecimal amountOutstanding, Sponsor sponsor, Student student, Boolean paidInFull) {
        this.feeIssueDate = feeIssueDate;
        this.amountOutstanding = amountOutstanding;
        this.sponsor = sponsor;
        this.student = student;
        this.paidInFull = paidInFull;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private Date feeIssueDate;
    private BigDecimal amountOutstanding;
    @OneToOne
    @JoinColumn(name = "sponsor_id")
    private Sponsor sponsor;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private Boolean paidInFull;

    public SponsorshipFees() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFeeIssueDate() {
        return feeIssueDate;
    }

    public void setFeeIssueDate(Date feeIssueDate) {
        this.feeIssueDate = feeIssueDate;
    }

    public BigDecimal getAmountOutstanding() {
        return amountOutstanding;
    }

    public void setAmountOutstanding(BigDecimal amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getPaidInFull() {
        return paidInFull;
    }

    public void setPaidInFull(Boolean paidInFull) {
        this.paidInFull = paidInFull;
    }

}
