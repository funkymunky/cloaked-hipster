package net.lsf.service;

import net.lsf.model.Fees;

import java.util.Date;

public interface FeesService {

    void updateFeeIssueDate(Date issueDate);
    Fees getCurrentFees();
}
