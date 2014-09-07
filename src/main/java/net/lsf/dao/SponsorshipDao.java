package net.lsf.dao;

import net.lsf.model.Sponsorship;

public interface SponsorshipDao {

    public void addSponsorship(Sponsorship sponsorship);
    public void updateSponsorship(Sponsorship sponsorship);
    public void updateSponsorship(Sponsorship sponsorship, int id);

}
