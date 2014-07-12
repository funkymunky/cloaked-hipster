package net.helloworld.service;

import net.helloworld.data.SponsorDTO;
import net.helloworld.model.Address;
import net.helloworld.model.Sponsor;
import net.helloworld.model.Student;

import java.util.List;
import java.util.Set;

/**
 * Date: 17/10/13
 * Time: 11:28 PM
 */
public interface SponsorService {

    void addSponsor(Sponsor sponsor);
    void updateSponsor(Sponsor sponsor);
    Sponsor getSponsor(int id);
    List<SponsorDTO> getAllSponsors();
    Set<SponsorDTO> getSponsorByName(String searchText);

    Address getAddressForSponsor(int id);
    void updateAddressForSponsor(int id, Address address);
    List<Student> getAllSponsoredKids(int sponsorId);
}
