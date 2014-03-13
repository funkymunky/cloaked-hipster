package net.helloworld.service;

import net.helloworld.model.Sponsorship;

public interface SponsorshipService {

    public void addSponsorhsip(Sponsorship sponsorship);
    public void updateSponsorship(Sponsorship sponsorship);
    public void updateSponsorship(Sponsorship sponsorship, int id);

}
