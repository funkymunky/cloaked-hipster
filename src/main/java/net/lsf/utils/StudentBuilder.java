package net.lsf.utils;

import net.lsf.model.*;

import java.util.Date;

public class StudentBuilder {
    private Integer id;
    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    private Address address;
    private Education education;
    private Sponsorship sponsorship;
    private Bank bank;
    private Comments comments;
    private String email;
    private String telephone;
    private String profilePic;


    public StudentBuilder() { }

    public Student buildStudent() {
        return new Student(id, lastName, firstName, dateOfBirth, address, education, sponsorship, bank, comments, email, telephone, profilePic);
    }

    public StudentBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public StudentBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder dateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public StudentBuilder address(Address address) {
        this.address = address;
        return this;
    }

    public StudentBuilder education(Education education) {
        this.education = education;
        return this;
    }

    public StudentBuilder sponsorship(Sponsorship sponsorship) {
        this.sponsorship = sponsorship;
        return this;
    }

    public StudentBuilder bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public StudentBuilder comments(Comments comments) {
        this.comments = comments;
        return this;
    }

    public StudentBuilder email(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public StudentBuilder profilePic(String profilePic) {
        this.profilePic = profilePic;
        return this;
    }
}