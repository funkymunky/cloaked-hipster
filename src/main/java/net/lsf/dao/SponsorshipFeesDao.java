package net.lsf.dao;

import net.lsf.model.SponsorshipFees;
import java.util.List;

public interface SponsorshipFeesDao {

    void saveNewSponsorshipFees(SponsorshipFees sponsorshipFees);

    List<SponsorshipFees> getOutstandingFees();

    void updatePaymentsMade(List<String> paidFees);

    List<SponsorshipFees> getFeesForSponsor(int id);
}
