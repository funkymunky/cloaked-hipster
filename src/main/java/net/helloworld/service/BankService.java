package net.helloworld.service;

import net.helloworld.model.Bank;

public interface BankService {

    public void addBank(Bank bank);
    public void updateBank(Bank bank, int id);
}
