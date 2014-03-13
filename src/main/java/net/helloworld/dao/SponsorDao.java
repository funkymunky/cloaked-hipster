package net.helloworld.dao;

import net.helloworld.model.Address;
import net.helloworld.model.Sponsor;

import java.util.List;

/**
 * Date: 17/10/13
 * Time: 11:10 PM
 */
public interface SponsorDao {

    public void addSponsor(Sponsor sponsor);
    public void updateSponsor(Sponsor sponsor);
    public Sponsor getSponsor(int id);
    public List<Sponsor> getAllSponsors();

    public Address getAddressForSponsor(int id);
    public void updateAddress(Sponsor sponsor, Address address);
}
