package net.helloworld.dao.impl;

import net.helloworld.dao.SponsorDao;
import net.helloworld.model.Address;
import net.helloworld.model.Sponsor;
import net.helloworld.model.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Date: 17/10/13
 * Time: 11:12 PM
 */
@Repository
@Transactional
public class SponsorDaoImpl implements SponsorDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addSponsor(Sponsor sponsor) {
        getCurrentSession().save(sponsor);
    }

    @Override
    public void updateSponsor(Sponsor sponsor) {
        Sponsor sponsorToUpdate = getSponsor(sponsor.getId());
        sponsorToUpdate.setFirstName(sponsor.getFirstName());
        sponsorToUpdate.setLastName(sponsor.getLastName());
        sponsorToUpdate.setPhone1(sponsor.getPhone1());
        sponsorToUpdate.setPhone2(sponsor.getPhone2());
        getCurrentSession().update(sponsorToUpdate);

    }

    @Override
    public void updateAddress(Sponsor sponsor, Address address) {
        Sponsor sponsorToUpdate = getSponsor(sponsor.getId());
        sponsorToUpdate.setAddress(address);
        getCurrentSession().update(sponsorToUpdate);
    }

    @Override
    public List<Student> getAllStudentsForSponsor(int id) {
        String sql = String.format("Select s from Student as s right outer join s.sponsorship as sp where sp.sponsor_id = %s order by s.lastName", id);
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        List<Student> list = query.list();

        return list;
    }

    @Override
    public Sponsor getSponsor(int id) {
        Sponsor sponsor = (Sponsor) getCurrentSession().get(Sponsor.class, id);
        return sponsor;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Sponsor> getAllSponsors() {
        return sessionFactory.getCurrentSession().createQuery("from Sponsor order by lastname").list();
    }

    @Override
    public Set<Sponsor> getAllSponsorsByName(String searchText) {
        return getAllSponsors().stream()
                .filter(sponsor ->
                    sponsor.getFirstName().toLowerCase().contains(searchText.toLowerCase()) ||
                    sponsor.getLastName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toSet());
    }

    @Override
    public Address getAddressForSponsor(int id) {
        Sponsor currentSponsor = getSponsor(id);
        return currentSponsor.getAddress();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
