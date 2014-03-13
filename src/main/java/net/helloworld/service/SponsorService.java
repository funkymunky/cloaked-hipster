package net.helloworld.service;

import net.helloworld.model.Address;
import net.helloworld.model.Sponsor;

import java.util.List;

/**
 * Date: 17/10/13
 * Time: 11:28 PM
 */
public interface SponsorService {

    public void addSponsor(Sponsor sponsor);
    public void updateSponsor(Sponsor sponsor);
    public Sponsor getSponsor(int id);
    public List<Sponsor> getAllSponsors();

    public Address getAddressForSponsor(int id);
    public void updateAddressForSponsor(int id, Address address);

}
