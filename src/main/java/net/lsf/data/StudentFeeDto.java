package net.lsf.data;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class StudentFeeDTO {

    private String monthlyAllowance;
    private String bankFee;
    private String exchangeRate;
    private String electedCurrency;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date paymentFrom;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date paymentTo;
    private String amountOutstanding;

    public StudentFeeDTO() {
    }

    public StudentFeeDTO(String monthlyAllowance, String bankFee, String exchangeRate, String electedCurrency, Date paymentFrom, Date paymentTo, String amountOutstanding) {
        this.monthlyAllowance = monthlyAllowance;
        this.bankFee = bankFee;
        this.exchangeRate = exchangeRate;
        this.electedCurrency = electedCurrency;
        this.paymentFrom = paymentFrom;
        this.paymentTo = paymentTo;
        this.amountOutstanding = amountOutstanding;
    }

    public String getMonthlyAllowance() {
        return monthlyAllowance;
    }

    public void setMonthlyAllowance(String monthlyAllowance) {
        this.monthlyAllowance = monthlyAllowance;
    }

    public String getBankFee() {
        return bankFee;
    }

    public void setBankFee(String bankFee) {
        this.bankFee = bankFee;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
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

    public Date getPaymentTo() {
        return paymentTo;
    }

    public void setPaymentTo(Date paymentTo) {
        this.paymentTo = paymentTo;
    }


    public String getAmountOutstanding() {
        return amountOutstanding;
    }

    public void setAmountOutstanding(String amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }
}
