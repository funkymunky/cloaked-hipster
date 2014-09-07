package net.lsf.service;


import net.lsf.model.SponsorshipFees;

import java.util.List;

public interface SponsorshipFeesService {

    void saveNewSponsorshipFees(SponsorshipFees sponsorshipFees);

    List<SponsorshipFees> getOutstandingFees();

    void updatePaymentsReceived(List<String> paidFees);

    List<SponsorshipFees> getFeesForSponsor(int id);
}
