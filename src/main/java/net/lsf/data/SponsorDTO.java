package net.lsf.data;

import net.lsf.model.Student;

import java.util.List;

public class  SponsorDTO {

    private int sponsorId;
    private String firstName;
    private String lastName;
    private String name;
    private String email;
    private String phone1;
    private String phone2;
    private List<Student> allSponsoredKids;


    public SponsorDTO(int sponsorId, String firstName, String lastName, String email, String phone1, String phone2, List<Student> allSponsoredKids) {
        this.sponsorId = sponsorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = String.format("%s %s", firstName, lastName);
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.allSponsoredKids = allSponsoredKids;

    }

    public int getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(int sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName, String lastName) {
        this.name = String.format("%s %s", firstName, lastName);
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Student> getAllSponsoredKids() {
        return allSponsoredKids;
    }

    public void setAllSponsoredKids(List<Student> allSponsoredKids) {
        this.allSponsoredKids = allSponsoredKids;
    }
}
