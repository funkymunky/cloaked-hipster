package net.helloworld.service.impl;

import net.helloworld.InstitutionType;
import net.helloworld.model.Bank;
import net.helloworld.model.Education;
import net.helloworld.model.Student;
import net.helloworld.service.WriterService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {

    @Override
    public void writeCsvFile(String fileName, String[] headerRow, List<Student> content, HttpServletResponse response) throws IOException {

        BufferedWriter writer = new BufferedWriter(response.getWriter());
        try {
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
            for (String h : headerRow) {
                writer.write(h);
                writer.write(",");
            }
            writer.newLine();

            for (Student eachStudent : content) {
                writer.write(eachStudent.getId().toString());
                writer.write(", '");
                writer.write(eachStudent.getLastName());
                writer.write(",");
                writer.write(eachStudent.getFirstName());
                writer.write("',");

                Education education = eachStudent.getEducation();
                if (education != null) {
                    if (education.getInstitutionType().equals(InstitutionType.School.name())) {
                        writer.write(education.getInstitutionName());
                    } else {
                        writer.write(education.getDegreeName());
                    }
                    writer.write(String.format(" (%s)", education.getYearOfStudy()));
                }
                writer.write(",");
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

        } catch (IOException ex) {
        } finally {
            writer.flush();
            writer.close();
        }
    }
}
