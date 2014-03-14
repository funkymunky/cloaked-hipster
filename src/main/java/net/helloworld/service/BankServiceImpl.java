package net.helloworld.service;

import net.helloworld.dao.BankDao;
import net.helloworld.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    @Override
    public void addBank(Bank bank) {
        bankDao.addBank(bank);
    }

    @Override
    public void updateBank(Bank bank, int id) {
        bankDao.updateBank(bank, id);
    }
}
