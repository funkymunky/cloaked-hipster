package net.lsf.dao.impl;

import net.lsf.dao.SponsorshipFeesDao;
import net.lsf.model.Sponsor;
import net.lsf.model.SponsorshipFees;
import net.lsf.service.FeesService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class SponsorshipFeesDaoImpl implements SponsorshipFeesDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private FeesService feesService;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveNewSponsorshipFees(SponsorshipFees sponsorshipFees) {
        getCurrentSession().save(sponsorshipFees);
    }

    @Override
    public List<SponsorshipFees> getOutstandingFees() {
        return sessionFactory.getCurrentSession().createQuery("from SponsorshipFees where paidInFull = false ").list();
    }

    @Override
    public void updatePaymentsMade(List<String> paidFees) {
        for (String fee : paidFees) {
            SponsorshipFees sponsorshipFee = (SponsorshipFees) getCurrentSession().get(SponsorshipFees.class, Integer.parseInt(fee));
            sponsorshipFee.setPaidInFull(true);
            getCurrentSession().update(sponsorshipFee);
        }
    }

    @Override
    public List<SponsorshipFees> getFeesForSponsor(int id) {
        Sponsor currentSponsor = (Sponsor) getCurrentSession().get(Sponsor.class, id);
        Date issueDate = feesService.getCurrentFees().getIssueDate();
        return sessionFactory.getCurrentSession().createQuery("from SponsorshipFees where sponsor = " + currentSponsor.getId() + " and feeIssueDate = '" + issueDate + "'").list();
    }

}
