package net.lsf.site.report;

import net.lsf.AgentType;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Student;
import net.lsf.service.ReportService;
import net.lsf.service.SponsorService;
import net.lsf.service.StudentService;
import net.lsf.service.WriterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class ReportController {

    public static final String[] HEADER_ROW = {"Student id", "Student name", "Year of study", "Account name", "Bank", "Branch", "Account number", "Standing order number"};

    private static final Logger LOG = Logger.getLogger("ReportController.class");

    @Autowired
    StudentService studentService;

    @Autowired
    WriterService writerService;

    @Autowired
    SponsorService sponsorService;

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/report/list", method = RequestMethod.GET)
    public String listAllStudents(Model model) {
        return "/reports/list";
    }


    @RequestMapping(value = "/report/applicationExpired", method = RequestMethod.GET)
    public String showStudentsWhereApplicationExpired(Model model) throws ReportException {
        List<Student> students = reportService.getStudentsBySponsorshipType(SponsorshipType.ApplicationExpired);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value = "/report/applicationExpired/school", method = RequestMethod.GET)
    public String showOnlySchoolStudentsWhereApplicationExpired(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.ApplicationExpired, InstitutionType.School);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value = "/report/applicationExpired/university", method = RequestMethod.GET)
    public String showOnlyUniveristyStudentsWhereApplicationExpired(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.ApplicationExpired, InstitutionType.University);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value="/report/applicationExpired/bank/{bankName}", method = RequestMethod.GET)
    public String showOnlyApplicationExpiredWithBank(@PathVariable String bankName, Model model) {
        List<Student> students = studentService.getAllStudentsWithBank(SponsorshipType.ApplicationExpired, bankName);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value="/report/applicationExpired/downloadCsv")
    public void downloadCsvForStudentsWhereApplicationExpired(HttpServletResponse response) throws IOException, ReportException {
        List<Student> listOfStudents = reportService.getStudentsBySponsorshipType(SponsorshipType.ApplicationExpired);
        writerService.writeCsvFile("studentsApplicationExpired.csv", HEADER_ROW, listOfStudents, response, false, false);
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
            LOG.error("Error in download", ex);
        } finally {
            writer.flush();
            writer.close();
        }

    }


}
