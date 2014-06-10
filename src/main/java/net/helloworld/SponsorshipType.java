package net.helloworld;

public enum SponsorshipType {
    AwaitingSponsorship("Awaiting sponsorship"),
    CurrentlySponsored("Currently sponsored"),
    FormerlySponsored("Formerly sponsored");

    private String description;

    private SponsorshipType(String description) {
        this.description = description;
    }

    public String getSponsorshipTypeValue() {
        return this.description;
    }

    public String getSponsorshipTypeName() {
        return this.name();
    }
}
