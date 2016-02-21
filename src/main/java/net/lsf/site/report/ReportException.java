package net.lsf.site.report;

public class ReportException extends Exception {

    public ReportException() {
        super("The report could not be generated - please contact your LSF support team.");
    }
}
