package net.lsf.service.impl;

import net.lsf.dao.SponsorDao;
import net.lsf.data.SponsorDTO;
import net.lsf.model.Address;
import net.lsf.model.Sponsor;
import net.lsf.model.Student;
import net.lsf.service.SponsorService;
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
    public Map<String, String> getMapOfAllSponsors() {
        Map<String, String> mapOfSponsors = new HashMap<>();
        List<Sponsor> allSponsors = sponsorDao.getAllSponsors();
        for (Sponsor sponsor : allSponsors) {
            mapOfSponsors.put(String.valueOf(sponsor.getId()), (sponsor.getLastName() + ", " + sponsor.getFirstName()));
        }

        return mapOfSponsors;
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

    @Override
    public List<Student> getAllSponsoredKids(int sponsorId) {
        List<Student> allStudents = sponsorDao.getAllStudentsForSponsor(sponsorId);
        return allStudents == null ? Collections.emptyList() : allStudents;
    }
}
