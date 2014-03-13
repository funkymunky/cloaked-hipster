package net.helloworld.service;

import net.helloworld.dao.SponsorDao;
import net.helloworld.model.Address;
import net.helloworld.model.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Date: 17/10/13
 * Time: 11:29 PM
 */
@Service
@Transactional
public class SponsorServiceImpl implements SponsorService {

    @Autowired
    private SponsorDao sponsorDao;

    @Override
    public void addSponsor(Sponsor sponsor) {
        sponsorDao.addSponsor(sponsor);
    }

    @Override
    public void updateSponsor(Sponsor sponsor) {
        sponsorDao.updateSponsor(sponsor);
    }

    @Override
    public Sponsor getSponsor(int id) {
        return sponsorDao.getSponsor(id);
    }

    @Override
    public List<Sponsor> getAllSponsors() {
        return sponsorDao.getAllSponsors();
    }

    @Override
    public Address getAddressForSponsor(int id) {
        Address address = sponsorDao.getAddressForSponsor(id);
        return address == null ? null : address;
    }

    @Override
    public void updateAddressForSponsor(int id, Address address) {
        Sponsor sponsor = getSponsor(id);
        sponsorDao.updateAddress(sponsor, address);
    }
}
