package net.helloworld.dao.impl;

import net.helloworld.dao.SponsorshipFeesDao;
import net.helloworld.model.SponsorshipFees;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SponsorshipFeesDaoImpl implements SponsorshipFeesDao {

    @Autowired
    private SessionFactory sessionFactory;

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

}
