package net.helloworld.service;

import net.helloworld.dao.AddressDao;
import net.helloworld.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Date: 17/10/13
 * Time: 11:29 PM
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public void addAddress(Address address) {
        addressDao.addAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressDao.updateAddress(address);
    }

    @Override
    public Address getAddress(int id) {
        return addressDao.getAddress(id);
    }

}
