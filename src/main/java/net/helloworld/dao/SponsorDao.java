package net.helloworld.dao;

import net.helloworld.model.Address;
import net.helloworld.model.Sponsor;
import net.helloworld.model.Student;

import java.util.List;
import java.util.Set;

/**
 * Date: 17/10/13
 * Time: 11:10 PM
 */
public interface SponsorDao {

    void addSponsor(Sponsor sponsor);
    void updateSponsor(Sponsor sponsor);
    Sponsor getSponsor(int id);
    List<Sponsor> getAllSponsors();
    Set<Sponsor> getAllSponsorsByName(String searchText);

    Address getAddressForSponsor(int id);
    void updateAddress(Sponsor sponsor, Address address);
    List<Student> getAllStudentsForSponsor(int id);
}
