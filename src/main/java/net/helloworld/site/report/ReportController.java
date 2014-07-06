package net.helloworld.site.report;

import net.helloworld.SponsorshipType;
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

        List<Student> sponsored = studentService.getAllStudents().stream()
                .filter(student -> student.getSponsorship().getSponsorshipType().equals(SponsorshipType.CurrentlySponsored.getName()))
                .collect(Collectors.toList());

        model.addAttribute("students", sponsored);
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value = "/report/awaitingSponsorship", method = RequestMethod.GET)
    public String showStudentsAwaitingSponsorship(Model model) {

        List<Student> awaitingSponsorship = studentService.getAllStudents().stream()
                .filter(student -> student.getSponsorship().getSponsorshipType().equals(SponsorshipType.AwaitingSponsorship.getName()))
                .collect(Collectors.toList());

        model.addAttribute("students", awaitingSponsorship);
        return "/reports/awaitingSponsorship";
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

}
