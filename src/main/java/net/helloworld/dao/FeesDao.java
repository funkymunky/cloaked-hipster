package net.helloworld.dao;

import net.helloworld.model.Fees;

public interface FeesDao {
    Fees getCurrentFeeIssueDate();
    void setFeeIssueDate(Fees fees);
}
