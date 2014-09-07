package net.lsf.dao;

import net.lsf.model.Bank;

public interface BankDao {

    public void addBank(Bank bank);
    public void updateBank(Bank bank, int id);
    public Bank getBank(int id);
}
