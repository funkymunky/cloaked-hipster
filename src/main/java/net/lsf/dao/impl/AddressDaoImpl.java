package net.lsf.dao.impl;

import net.lsf.dao.AddressDao;
import net.lsf.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Date: 17/10/13
 * Time: 11:12 PM
 */
@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addAddress(Address address) {
        getCurrentSession().save(address);
    }

    @Override
    public void updateAddress(Address address) {
        Address addressToUpdate = getAddress(address.getId());
        updateAddressDetails(address, addressToUpdate);

    }

    @Override
    public void updateAddress(Address address, int id) {
        Address addressToUpdate = getAddress(id);
        updateAddressDetails(address, addressToUpdate);
    }


    @Override
    public Address getAddress(int id) {
        Address address = (Address) getCurrentSession().get(Address.class, id);
        return address;

    }

    private void updateAddressDetails(Address address, Address addressToUpdate) {
        addressToUpdate.setAddressLine1(address.getAddressLine1());
        addressToUpdate.setAddressLine2(address.getAddressLine2());
        addressToUpdate.setSuburb(address.getSuburb());
        addressToUpdate.setPostcode(address.getPostcode());
        addressToUpdate.setState(address.getState());
        addressToUpdate.setCountry(address.getCountry());
        addressToUpdate.setTelephone(address.getTelephone());
        getCurrentSession().update(addressToUpdate);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
