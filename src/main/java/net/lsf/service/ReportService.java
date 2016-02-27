package net.lsf.service;

import net.lsf.AgentType;
import net.lsf.BankInstitution;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Student;
import net.lsf.site.report.ReportException;

import java.util.List;

public interface ReportService {

    List<Student> getStudentsBySponsorshipType(SponsorshipType sponsorshipType) throws ReportException;
    List<Student> getStudentsByAgentType(AgentType agentType) throws ReportException;
    List<Student> getStudentsByAgentType(AgentType agentType, InstitutionType institutionType) throws ReportException;
    List<Student> getStudentsByBank(SponsorshipType sponsorshipType, BankInstitution bankInstiution) throws ReportException;
    List<Student> getStudentsByInstitutionType(SponsorshipType sponsorshipType, InstitutionType institutionType) throws ReportException;
}
