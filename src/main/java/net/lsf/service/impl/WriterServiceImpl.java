package net.lsf.service.impl;

import net.lsf.InstitutionType;
import net.lsf.model.Bank;
import net.lsf.model.Education;
import net.lsf.model.Student;
import net.lsf.service.SponsorService;
import net.lsf.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WriterServiceImpl implements WriterService {

    @Autowired
    SponsorService sponsorService;

    @Override
    public void writeCsvFile(String fileName, String[] headerRow, List<Student> content, HttpServletResponse response, boolean studentSpecifics, boolean addSponsorDetails) throws IOException {

        BufferedWriter writer = new BufferedWriter(response.getWriter());
        try {
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
            for (String h : headerRow) {
                writer.write(h);
                writer.write(",");
            }
            writer.newLine();
            if (!studentSpecifics) {
                writeContent(content, writer, addSponsorDetails);
            } else {
                writeStudentSpecific(content, writer);
            }

        } catch (IOException ex) {
        } finally {
            writer.flush();
            writer.close();
        }
    }

    private void writeStudentSpecific(List<Student> content, BufferedWriter writer) throws IOException {
        for (Student eachStudent : content) {
            writer.write(eachStudent.getId().toString());
            writer.write(", \"");
            writer.write(eachStudent.getLastName());
            writer.write(",");
            writer.write(eachStudent.getFirstName());
            writer.write("\",");
            writer.write(getAgeFromDateOfBirth(eachStudent.getDateOfBirth()));
            writer.write(",");

            Education education = eachStudent.getEducation();
            if (education != null) {
                writer.write(getCurrentYearOfStudy(education.getApplicationDate(), education.getYearOfStudyAsAtApplicationDate()));
                writer.write(",");

                if (education.getInstitutionType().equals(InstitutionType.School.name())) {
                    writer.write(education.getInstitutionName());
                } else {
                    writer.write(education.getInstitutionName() + " (" + education.getDegreeName() + ")");
                }
            }
            writer.newLine();
        }
    }

    private void writeContent(List<Student> content, BufferedWriter writer, boolean addSponsorName) throws IOException {
        for (Student eachStudent : content) {
            writer.write(eachStudent.getId().toString());
            writer.write(", \"");
            writer.write(eachStudent.getLastName());
            writer.write(",");
            writer.write(eachStudent.getFirstName());
            writer.write("\",");

            Education education = eachStudent.getEducation();
            if (education != null) {
                writer.write(getCurrentYearOfStudy(education.getApplicationDate(), education.getYearOfStudyAsAtApplicationDate()));
                writer.write(",");
                if (education.getInstitutionType().equals(InstitutionType.School.name())) {
                    writer.write(education.getInstitutionName());
                } else {
                    writer.write(education.getInstitutionName() + " (" + education.getDegreeName() + ")");
                }
            }
            writer.write(",");

            if (addSponsorName) {
                writer.write("\"");
                writer.write(getSponsorNameForStudent(eachStudent.getSponsorship().getSponsor()));
                writer.write("\"");
                writer.write(",");
            }

            Bank bank = eachStudent.getBank();
            if (bank != null) {
                writer.write(bank.getAccountName());
                writer.write(",");
                writer.write(bank.getBank());
                writer.write(",");
                writer.write(bank.getBranch());
                writer.write(",");
                writer.write(bank.getAccountNumber());
                writer.write(",");
                writer.write(bank.getStandingOrder());
            } else {
                writer.write(",,,,");
            }
            writer.newLine();
        }
    }

    private String getSponsorNameForStudent(Long sponsor) {
        Map<String,String> mapOfAllSponsors = sponsorService.getMapOfAllSponsors();
        return mapOfAllSponsors.get(String.valueOf(sponsor));
    }

    private String getAgeFromDateOfBirth(Date dateOfBirth) {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();

        LocalDate ld = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Year y = Year.parse(ld.toString(), DateTimeFormatter.ISO_DATE);
        return String.valueOf(year - y.getValue());
    }

    private String getCurrentYearOfStudy(Date applicationDate, int yearOfStudyAsAtApplicationDate) {
        Year now = Year.now();

        LocalDate localDate = applicationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Year appDate = Year.parse(localDate.toString(), DateTimeFormatter.ISO_DATE);
        int numYears = now.getValue() - appDate.getValue();

        return String.valueOf(numYears + yearOfStudyAsAtApplicationDate);
    }
}
