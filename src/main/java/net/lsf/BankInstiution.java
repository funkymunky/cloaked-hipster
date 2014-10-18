package net.lsf;

public enum BankInstiution {
    BankOfCeylon(1, "Bank of Ceylon"),
    PeoplesBank(2, "Peoples Bank"),
    SampathBank(3, "Sampath Bank"),
    HattonNationalBank(4, "Hatton National Bank"),
    NationalSavingsBank(5, "National Savings Bank"),
    CommercialBank(6, "Commercial Bank"),
    SeylanBank(7, "Seylan Bank"),
    HingurukaduwaGramiyaBank(8, "Hingurukaduwa Gramiya Bank"),
    NationalTrustBank(9, "National Trust Bank");

    private int code;
    private String description;

    BankInstiution(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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