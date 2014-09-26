package net.lsf.model;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Date: 17/10/13
 * Time: 11:01 PM
 */
@Entity
@Table(name = "SPONSORS")
public class Sponsor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String lastName;
    private String firstName;
    private String phone1;
    private String phone2;
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return StringUtils.capitalize(lastName) + ", " + firstName;
    }
}
