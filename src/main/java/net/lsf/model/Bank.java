package net.lsf.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="BANK")
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String accountName;
    private String accountNumber;
    private String bank;
    private String branch;
    private String standingOrder;

    public Bank() { }

    public Bank(String accountName, String accountNumber, String bank, String branch, String standingOrder) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.bank = bank;
        this.branch = branch;
        this.standingOrder = standingOrder;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStandingOrder() {
        return standingOrder;
    }

    public void setStandingOrder(String standingOrder) {
        this.standingOrder = standingOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
