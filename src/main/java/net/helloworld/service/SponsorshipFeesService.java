package net.helloworld.service;


import net.helloworld.model.SponsorshipFees;

public interface SponsorshipFeesService {

    void saveNewSponsorshipFees(SponsorshipFees sponsorshipFees);

    SponsorshipFees getOutstandingFees();
}
