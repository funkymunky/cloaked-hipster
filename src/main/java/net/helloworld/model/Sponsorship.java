package net.helloworld.model;


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

    @OneToOne
    @JoinColumn(name = "sponsor_id")
    private Sponsor sponsor;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String sponsorshipType;
    private String electedCurrency;
    private Date paymentFrom;
    private Date paymentTill;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
