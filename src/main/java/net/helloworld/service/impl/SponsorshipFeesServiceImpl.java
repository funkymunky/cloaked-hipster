package net.helloworld.service.impl;

import net.helloworld.dao.SponsorshipFeesDao;
import net.helloworld.model.SponsorshipFees;
import net.helloworld.service.SponsorshipFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SponsorshipFeesServiceImpl implements SponsorshipFeesService {

    @Autowired
    SponsorshipFeesDao sponsorshipFeesDao;

    @Override
    public void saveNewSponsorshipFees(SponsorshipFees sponsorshipFees) {
        sponsorshipFeesDao.saveNewSponsorshipFees(sponsorshipFees);
    }
}
