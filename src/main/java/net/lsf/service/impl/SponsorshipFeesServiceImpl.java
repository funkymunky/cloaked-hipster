package net.lsf.service.impl;

import net.lsf.dao.SponsorshipFeesDao;
import net.lsf.model.SponsorshipFees;
import net.lsf.service.SponsorshipFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SponsorshipFeesServiceImpl implements SponsorshipFeesService {

    @Autowired
    SponsorshipFeesDao sponsorshipFeesDao;

    @Override
    public void saveNewSponsorshipFees(SponsorshipFees sponsorshipFees) {
        sponsorshipFeesDao.saveNewSponsorshipFees(sponsorshipFees);
    }

    @Override
    public List<SponsorshipFees> getOutstandingFees() {
        return sponsorshipFeesDao.getOutstandingFees();
    }

    @Override
    public void updatePaymentsReceived(List<String> paidFees) {
        sponsorshipFeesDao.updatePaymentsMade(paidFees);
    }

    @Override
    public List<SponsorshipFees> getFeesForSponsor(int id) {
        return sponsorshipFeesDao.getFeesForSponsor(id);
    }
}
