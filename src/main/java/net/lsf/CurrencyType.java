package net.lsf;

/**
 * Created by bigboss on 1/26/14.
 */

public enum CurrencyType {
    School,
    University,
    Other;

    public String getInstitutionTypeName() {
        return this.name();
    }
}
