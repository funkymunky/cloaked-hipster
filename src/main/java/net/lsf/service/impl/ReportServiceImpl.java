package net.lsf.service.impl;

import net.lsf.AgentType;
import net.lsf.BankInstitution;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Bank;
import net.lsf.model.Student;
import net.lsf.service.ReportService;
import net.lsf.service.StudentService;
import net.lsf.site.report.ReportException;
import net.lsf.utils.BankBuilder;
import net.lsf.utils.StudentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private StudentService studentService;

    @Override
    public List<Student> getStudentsBySponsorshipType(SponsorshipType sponsorshipType) throws ReportException {
        return studentService.getAllStudents().stream()
                .filter(student -> student.getSponsorship().getSponsorshipType().equals(sponsorshipType.getName()))
                .map(getStudentForReport())
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsByAgentType(AgentType agentType) throws ReportException {
        return studentService.getAllStudents().stream()
                .filter(student -> student.getEducation() != null &&
                        student.getEducation().getAgent() != null &&
                        student.getEducation().getAgent().equals(agentType.getName()))
                .map(getStudentForReport())
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsByAgentType(AgentType agentType, InstitutionType institutionType) throws ReportException {
        return studentService.getAllStudents().stream()
                .filter(student -> student.getEducation() != null &&
                        student.getEducation().getAgent() != null &&
                        student.getEducation().getAgent().equals(agentType.getName()) &&
                        student.getEducation().getInstitutionType().equals(institutionType.name()))
                .map(getStudentForReport())
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsByBank(SponsorshipType sponsorshipType, BankInstitution bankInstiution) throws ReportException {
        return studentService.getAllStudents().stream()
                .filter(student -> student.getSponsorship().getSponsorshipType().equals(sponsorshipType.getName()) &&
                        student.getBank().getBank().equals(bankInstiution.getName()))
                .map(getStudentForReport())
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsByInstitutionType(SponsorshipType sponsorshipType, InstitutionType institutionType) throws ReportException {
        return studentService.getAllStudents().stream()
                .filter(student -> student.getSponsorship().getSponsorshipType().equals(sponsorshipType.getName()) &&
                        student.getEducation().getInstitutionType().equals(institutionType.getInstitutionTypeName()))
                .map(getStudentForReport())
                .collect(Collectors.toList());
    }

    private Function<? super Student, ? extends Student> getStudentForReport() throws ReportException {
        return student -> {
            Bank studentBank = student.getBank();
            return new StudentBuilder()
                    .id(student.getId())
                    .lastName(student.getLastName())
                    .firstName(student.getFirstName())
                    .dateOfBirth(student.getDateOfBirth())
                    .education(student.getEducation())
                    .sponsorship(student.getSponsorship())
                    .bank((studentBank == null) ? new Bank() : new BankBuilder()
                            .accountName(studentBank.getAccountName())
                            .accountNumber(studentBank.getAccountNumber())
                            .standingOrder(studentBank.getStandingOrder())
                            .branch(studentBank.getBranch())
                            .bankName(studentBank.getBank().equals("") ? "" : BankInstitution.valueOf(student.getBank().getBank()).getDescription())
                            .buildBank())
                    .buildStudent();
        };
    }

}
