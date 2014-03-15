package net.helloworld.service;

import net.helloworld.model.Sponsorship;

public interface SponsorshipService {

    void addSponsorhsip(Sponsorship sponsorship);
    void updateSponsorship(Sponsorship sponsorship);
    void updateSponsorship(Sponsorship sponsorship, int id);

}
