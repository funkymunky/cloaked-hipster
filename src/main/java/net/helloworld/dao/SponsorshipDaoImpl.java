package net.helloworld.dao;

import net.helloworld.model.Sponsorship;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SponsorshipDaoImpl implements SponsorshipDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addSponsorship(Sponsorship sponsorship) {
        getCurrentSession().save(sponsorship);
    }

    @Override
    public void updateSponsorship(Sponsorship sponsorship) {
        Sponsorship sponsorshipToUpdate = getSponsorship(sponsorship.getId());
        sponsorshipToUpdate.setSponsor(sponsorship.getSponsor());
        sponsorshipToUpdate.setStudent(sponsorship.getStudent());
        sponsorshipToUpdate.setSponsorshipType(sponsorship.getSponsorshipType());
        sponsorshipToUpdate.setElectedCurrency(sponsorship.getElectedCurrency());
        sponsorshipToUpdate.setPaymentFrom(sponsorship.getPaymentFrom());
        sponsorshipToUpdate.setPaymentTill(sponsorship.getPaymentTill());
        getCurrentSession().update(sponsorshipToUpdate);
    }

    private Sponsorship getSponsorship(int id) {
        Sponsorship sponsorship = (Sponsorship) getCurrentSession().get(Sponsorship.class, id);
        return sponsorship;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
