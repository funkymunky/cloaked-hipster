package net.helloworld.dao.impl;

import net.helloworld.dao.BankDao;
import net.helloworld.model.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BankDaoImpl implements BankDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addBank(Bank bank) {
        getCurrentSession().save(bank);
    }

    @Override
    public void updateBank(Bank bank, int id) {
        Bank bankToUpdate = getBank(id);
        updateBankDetails(bank, bankToUpdate);
    }

    public void updateBankDetails(Bank bank, Bank bankToUpdate) {
        bankToUpdate.setAccountName(bank.getAccountName());
        bankToUpdate.setAccountNumber(bank.getAccountNumber());
        bankToUpdate.setBank(bank.getBank());
        bankToUpdate.setBranch(bank.getBranch());
        bankToUpdate.setStandingOrder(bank.getStandingOrder());
    }

    @Override
    public Bank getBank(int id) {
        Bank bank = (Bank) getCurrentSession().get(Bank.class, id);
        return bank;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
