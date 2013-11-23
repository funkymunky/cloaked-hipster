package net.helloworld.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="ADDRESS")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String addressLine1;
    private String addressLine2;
    private String suburb;
    private String postcode;
    private String state;
    private String country;
    private String telephone;


    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        if (id == null) {
            return "";
        }
        return String.format("Address:"
                               + " Id: %s"
                               + " Line1: %s "
                               + " Line2: %s "
                               + " Suburb: %s "
                               + " Postcode: %s"
                               + " State: %s "
                               + " Country: %s" , getId().toString(), getAddressLine1(), getAddressLine2(), getSuburb(), getPostcode(), getState(), getCountry());
    }
}
