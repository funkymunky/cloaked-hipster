package net.helloworld.site.report;

import net.helloworld.InstitutionType;
import net.helloworld.SponsorshipType;
import net.helloworld.model.Bank;
import net.helloworld.model.Education;
import net.helloworld.model.Student;
import net.helloworld.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReportController {

    @Autowired
    StudentService studentService;


    @RequestMapping(value = "/report/list", method = RequestMethod.GET)
    public String listAllStudents(Model model) {
        return "/reports/list";
    }

    @RequestMapping(value = "/report/currentlySponsored", method = RequestMethod.GET)
    public String showCurrentlySponsoredStudents(Model model) {
        List<Student> sponsored = getStudentsBySponsorshipType(SponsorshipType.CurrentlySponsored);
        model.addAttribute("students", sponsored);
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value="/report/currentlySponsored/downloadCsv")
    public void downloadCsvForCurrentlySponsoredStudents(HttpServletResponse response) throws IOException {
        List<Student> listOfStudents = getStudentsBySponsorshipType(SponsorshipType.CurrentlySponsored);
        writeToCsv(response, listOfStudents);
    }

    @RequestMapping(value = "/report/awaitingSponsorship", method = RequestMethod.GET)
    public String showStudentsAwaitingSponsorship(Model model) {
        List<Student> awaitingSponsorship = getStudentsBySponsorshipType(SponsorshipType.AwaitingSponsorship);
        model.addAttribute("students", awaitingSponsorship);
        return "/reports/awaitingSponsorship";
    }

    @RequestMapping(value="/report/awaitingSponsorship/downloadCsv")
    public void downloadCsvForStudentsAwaitingSponsorshipt(HttpServletResponse response) throws IOException {
        List<Student> listOfStudents = getStudentsBySponsorshipType(SponsorshipType.AwaitingSponsorship);
        writeToCsv(response, listOfStudents);
    }

    @RequestMapping(value = "/report/allStudents", method = RequestMethod.GET)
    public String showAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();

        model.addAttribute("students", students);
        return "/reports/allStudents";
    }

    @RequestMapping(value = "/report/allStudents/downloadCSV")
    public void downloadCSV(HttpServletResponse response) throws IOException {

        String [] header = { "First Name", "Last Name", "Date of Birth", "Institution Type" };

        List<Student> listStudents = studentService.getAllStudents();

        BufferedWriter writer = new BufferedWriter(response.getWriter());
        try {
            response.setHeader("Content-Disposition", "attachment; filename=\"mytextfile.csv\"");
            for (String h : header) {
                writer.write(h);
                writer.write(",");
            }
            writer.newLine();

            for (Student aStudent : listStudents) {
                writer.write(aStudent.getFirstName());
                writer.write(",");
                writer.write(aStudent.getLastName());
                writer.write(",");
                writer.write(aStudent.getDateOfBirth().toString());
                writer.write(",");
                writer.write(aStudent.getEducation().getInstitutionType());
                writer.newLine();
            }

        } catch (IOException ex) {
        } finally {
            writer.flush();
            writer.close();
        }

    }

    private List<Student> getStudentsBySponsorshipType(SponsorshipType sponsorshipType) {
        return studentService.getAllStudents().stream()
                .filter(student -> student.getSponsorship().getSponsorshipType().equals(sponsorshipType.getName()))
                .collect(Collectors.toList());
    }

    private void writeToCsv(HttpServletResponse response, List<Student> listOfStudents) throws IOException {
        String[] headerRow = {"Student id", "Student name", "Year of study", "Account name", "Bank", "Branch", "Account number", "Standing order number"};

        BufferedWriter writer = new BufferedWriter(response.getWriter());
        String fileName = "currentlySponsoredStudents.csv";

        try {
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
            for (String h : headerRow) {
                writer.write(h);
                writer.write(",");
            }
            writer.newLine();

            for (Student aStudent : listOfStudents) {
                writer.write(aStudent.getId().toString());
                writer.write(", '");
                writer.write(aStudent.getLastName());
                writer.write(",");
                writer.write(aStudent.getFirstName());
                writer.write("',");

                Education education = aStudent.getEducation();
                if (education != null) {
                    if (education.getInstitutionType().equals(InstitutionType.School.name())) {
                        writer.write(education.getInstitutionName());
                    } else {
                        writer.write(education.getDegreeName());
                    }
                    writer.write(String.format(" (%s)", education.getYearOfStudy()));
                }
                writer.write(",");
                Bank bank = aStudent.getBank();
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
