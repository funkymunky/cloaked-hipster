package net.helloworld;

public enum SponsorshipType {
    FormerlySponsored("Formerly sponsored"),
    AwaitingSponsorship("Awaiting sponsorship"),
    CurrentlySponsored("Currently sponsored");

    private String description;

    private SponsorshipType(String description) {
        this.description = description;
    }

    public String getSponsorshipTypeValue() {
        return this.description;
    }
}
