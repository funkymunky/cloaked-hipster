package net.lsf.data;

public class SponsorDTO {

    private int sponsorId;
    private String firstName;
    private String lastName;
    private String name;

    public SponsorDTO(int sponsorId, String firstName, String lastName) {
        this.sponsorId = sponsorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = String.format("%s %s", firstName, lastName);
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
}
