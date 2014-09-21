package net.lsf.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SPONSORSHIP")
public class Sponsorship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String sponsorshipType;
    private String electedCurrency;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date startDate;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date endDate;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date paymentFrom;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date paymentTill;
    private Long sponsor_id;
    private Long student_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSponsorshipType() {
        return sponsorshipType;
    }

    public void setSponsorshipType(String sponsorshipType) {
        this.sponsorshipType = sponsorshipType;
    }

    public String getElectedCurrency() {
        return electedCurrency;
    }

    public void setElectedCurrency(String electedCurrency) {
        this.electedCurrency = electedCurrency;
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

    public Date getPaymentFrom() {
        return paymentFrom;
    }

    public void setPaymentFrom(Date paymentFrom) {
        this.paymentFrom = paymentFrom;
    }

    public Date getPaymentTill() {
        return paymentTill;
    }

    public void setPaymentTill(Date paymentTill) {
        this.paymentTill = paymentTill;
    }

    public Long getSponsor() {
        return sponsor_id;
    }

    public void setSponsor(Long sponsor) {
        this.sponsor_id = sponsor;
    }

    public Long getStudent() {
        return student_id;
    }

    public void setStudent(Long student) {
        this.student_id = student;
    }
}
