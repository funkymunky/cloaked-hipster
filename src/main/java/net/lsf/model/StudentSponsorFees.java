package net.lsf.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "STUDENTSPONSORFEES")
public class StudentSponsorFees implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private Long student_id;

    private Long sponsor_id;

    private BigDecimal bankFee;

    private BigDecimal exchangeRate;

    private BigDecimal amountToPay;

    public StudentSponsorFees() { }

    public StudentSponsorFees(Long student_id, Long sponsor_id, BigDecimal bankFee, BigDecimal exchangeRate, BigDecimal amountToPay) {
        this.student_id = student_id;
        this.sponsor_id = sponsor_id;
        this.bankFee = bankFee;
        this.exchangeRate = exchangeRate;
        this.amountToPay = amountToPay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStudent() {
        return student_id;
    }

    public void setStudent(Long student_id) {
        this.student_id = student_id;
    }

    public Long getSponsor() {
        return sponsor_id;
    }

    public void setSponsor(Long sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public BigDecimal getBankFee() {
        return bankFee;
    }

    public void setBankFee(BigDecimal bankFee) {
        this.bankFee = bankFee;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(BigDecimal amountToPay) {
        this.amountToPay = amountToPay;
    }
}
