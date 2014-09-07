package net.lsf.dao;

import net.lsf.model.Fees;

public interface FeesDao {
    Fees getCurrentFeeIssueDate();
    void setFeeIssueDate(Fees fees);
}
