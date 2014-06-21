package net.helloworld.service;

import net.helloworld.dao.SponsorDao;
import net.helloworld.data.SponsorDTO;
import net.helloworld.model.Address;
import net.helloworld.model.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public List<SponsorDTO> getAllSponsors() {
        List<SponsorDTO> allSponsorsDTO = new ArrayList<SponsorDTO>();
        List<Sponsor> allSponsors = sponsorDao.getAllSponsors();
        for (Sponsor sponsor : allSponsors) {
            SponsorDTO sponsorDTO = new SponsorDTO(sponsor.getId(), sponsor.getFirstName(), sponsor.getLastName());
            allSponsorsDTO.add(sponsorDTO);
        }

        Collections.sort(allSponsorsDTO, new Comparator<SponsorDTO>() {
            @Override
            public int compare(SponsorDTO sponsor1, SponsorDTO sponsor2) {
                return sponsor1.getLastName().compareTo(sponsor2.getLastName());
            }
        });

        return allSponsorsDTO;
    }

    @Override
    public Set<SponsorDTO> getSponsorByName(String searchText) {
        Set<SponsorDTO> listOfSponsorsDTO = new HashSet<>();

        Set<Sponsor> listOfSponsors = sponsorDao.getAllSponsorsByName(searchText);
        for (Sponsor sponsor : listOfSponsors) {
            SponsorDTO sponsorDTO = new SponsorDTO(sponsor.getId(), sponsor.getFirstName(), sponsor.getLastName());
            listOfSponsorsDTO.add(sponsorDTO);
        }
        return listOfSponsorsDTO;
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
