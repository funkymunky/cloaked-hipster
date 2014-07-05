package net.helloworld.service.impl;

import net.helloworld.dao.SponsorshipDao;
import net.helloworld.model.Sponsorship;
import net.helloworld.service.SponsorshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SponsorshipServiceImpl implements SponsorshipService {

    @Autowired
    SponsorshipDao sponsorshipDao;

    @Override
    public void addSponsorhsip(Sponsorship sponsorship) {
        sponsorshipDao.addSponsorship(sponsorship);
    }

    @Override
    public void updateSponsorship(Sponsorship sponsorship) {
        sponsorshipDao.updateSponsorship(sponsorship);
    }

    @Override
    public void updateSponsorship(Sponsorship sponsorship, int id) {
        sponsorshipDao.updateSponsorship(sponsorship, id);
    }
}
