package net.lsf;

/**
 * Created by bigboss on 1/26/14.
 */

public enum InstitutionType {
    School,
    University,
    Other;

    public String getInstitutionTypeName() {
        return this.name();
    }
}
