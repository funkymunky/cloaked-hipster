package net.helloworld.service;

import net.helloworld.model.Address;

/**
 * Date: 17/10/13
 * Time: 11:28 PM
 */
public interface AddressService {

    public void addAddress(Address address);
    public void updateAddress(Address address);
    public void updateAddress(Address address, int id);
    public Address getAddress(int id);
}
