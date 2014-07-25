package net.helloworld.service;


import net.helloworld.model.SponsorshipFees;

import java.util.List;

public interface SponsorshipFeesService {

    void saveNewSponsorshipFees(SponsorshipFees sponsorshipFees);

    List<SponsorshipFees> getOutstandingFees();
}
