package net.helloworld.service.impl;

import net.helloworld.dao.FeesDao;
import net.helloworld.model.Fees;
import net.helloworld.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FeesServiceImpl implements FeesService {

    @Autowired
    private FeesDao feesDao;

    @Override
    public Fees getCurrentFees() {
        return feesDao.getCurrentFeeIssueDate();
    }

    @Override
    public void updateFeeIssueDate(Date issueDate) {
        Fees newFee = new Fees();
        newFee.setIssueDate(issueDate);
        feesDao.setFeeIssueDate(newFee);
    }
}

