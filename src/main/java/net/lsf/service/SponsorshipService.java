package net.lsf.service;

import net.lsf.model.Sponsorship;

public interface SponsorshipService {

    void addSponsorhsip(Sponsorship sponsorship);
    void updateSponsorship(Sponsorship sponsorship);
    void updateSponsorship(Sponsorship sponsorship, int id);

}
