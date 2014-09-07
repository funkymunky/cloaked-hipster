package net.lsf.service.impl;

import net.lsf.dao.FeesDao;
import net.lsf.model.Fees;
import net.lsf.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

