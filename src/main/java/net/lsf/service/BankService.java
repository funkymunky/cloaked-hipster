package net.lsf.service;

import net.lsf.model.Bank;

public interface BankService {

    public void addBank(Bank bank);
    public void updateBank(Bank bank, int id);
}
