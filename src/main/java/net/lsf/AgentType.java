package net.lsf;

public enum AgentType {
    LAF(1, "LAF"),
    Panama(2, "Panama"),
    Horizon(3, "Horizon"),
    AUS(4, "AUS"),
    LSF(5, "LSF"),
    Website(6, "Website"),
    HomeStartLanka(7, "Home Start Lanka"),
    LSFMembers(8, "LSF Members"),
    KRMV(9, "KRMV"),
    SuwaSevenaBoysOrphange(10, "Suwa Sevena Boys Orphanage"),
    SevalankaFoundation(11, "Sevalanka Foundation"),
    SamPrince(12, "Sam Prince");

    private int code;
    private String description;

    AgentType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name();
    }
}
