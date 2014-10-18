package net.lsf.utils;

import net.lsf.model.Bank;

public class BankBuilder {

    private String accountName;
    private String accountNumber;
    private String bank;
    private String branch;
    private String standingOrder;

    public BankBuilder() { }

    public Bank buildBank() {
        return new Bank(accountName, accountNumber, bank, branch, standingOrder);
    }

    public BankBuilder accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public BankBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BankBuilder bankName(String bankName) {
        this.bank = bankName;
        return this;
    }

    public BankBuilder branch(String branch) {
        this.branch = branch;
        return this;
    }

    public BankBuilder standingOrder(String standingOrder) {
        this.standingOrder = standingOrder;
        return this;
    }


}
