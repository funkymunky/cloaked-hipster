package net.helloworld.service;

import net.helloworld.model.Fees;

import java.util.Date;
import java.util.List;

public interface FeesService {

    void updateFeeIssueDate(Date issueDate);
    Fees getCurrentFees();
}
