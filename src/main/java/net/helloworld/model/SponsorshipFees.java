package net.helloworld.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "FEES")
public class SponsorshipFees {

    private static final long serialVersionUID = 1L;

    public SponsorshipFees(Date feeIssueDate, BigDecimal amountOutstanding, Long sponsor_id, Long student_id, boolean paidInFull) {
        this.feeIssueDate = feeIssueDate;
        this.amountOutstanding = amountOutstanding;
        this.sponsor_id = sponsor_id;
        this.student_id = student_id;
        this.paidInFull = paidInFull;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private Date feeIssueDate;
    private BigDecimal amountOutstanding;
    private Long sponsor_id;
    private Long student_id;
    private boolean paidInFull;

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

    public Long getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(Long sponsor) {
        this.sponsor_id = sponsor;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student) {
        this.student_id = student;
    }

    public boolean isPaidInFull() {
        return paidInFull;
    }

    public void setPaidInFull(boolean paidInFull) {
        this.paidInFull = paidInFull;
    }

}
