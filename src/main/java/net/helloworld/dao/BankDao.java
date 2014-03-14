package net.helloworld.dao;

import net.helloworld.model.Bank;

public interface BankDao {

    public void addBank(Bank bank);
    public void updateBank(Bank bank, int id);
    public Bank getBank(int id);
}
