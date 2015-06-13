package net.lsf.service;

import net.lsf.data.SponsorDTO;
import net.lsf.model.Address;
import net.lsf.model.Sponsor;
import net.lsf.model.Student;

import java.util.List;
import java.util.Map;
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
    Map<String, String> getMapOfAllSponsors();
    Set<SponsorDTO> getSponsorByName(String searchText);

    Address getAddressForSponsor(int id);
    void updateAddressForSponsor(int id, Address address);
    List<Student> getAllSponsoredKids(int sponsorId);
}
