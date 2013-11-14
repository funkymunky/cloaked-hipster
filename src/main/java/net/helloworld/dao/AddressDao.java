package net.helloworld.dao;

import net.helloworld.model.Address;

/**
 * Date: 15/06/13
 * Time: 7:19 PM
 */
public interface AddressDao {

    public void addAddress(Address address);
    public void updateAddress(Address address);
    public void updateAddress(Address address, int id);
    public Address getAddress(int id);

}
