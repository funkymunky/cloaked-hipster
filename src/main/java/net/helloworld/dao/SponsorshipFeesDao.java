package net.helloworld.dao;

import net.helloworld.model.SponsorshipFees;
import java.util.List;

public interface SponsorshipFeesDao {

    void saveNewSponsorshipFees(SponsorshipFees sponsorshipFees);

    List<SponsorshipFees> getOutstandingFees();

    void updatePaymentsMade(List<String> paidFees);

    List<SponsorshipFees> getFeesForSponsor(int id);
}
