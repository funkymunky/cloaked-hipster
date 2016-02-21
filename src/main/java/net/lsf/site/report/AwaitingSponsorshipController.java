package net.lsf.site.report;

import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Student;
import net.lsf.service.ReportService;
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
import java.io.IOException;
import java.util.List;

@Controller
public class AwaitingSponsorshipController {

    @Autowired
    StudentService studentService;

    @Autowired
    WriterService writerService;

    @Autowired
    ReportService reportService;

    final static Logger LOG = Logger.getLogger("AwaitingSponsorshipController.class");

    private static final String[] AWAITING_SPONRSORSHIP_HEADER_ROW = {"Student id", "Student name", "Age", "Year of study", "School / University"};

    @RequestMapping(value = "/report/awaitingSponsorship", method = RequestMethod.GET)
    public String showStudentsAwaitingSponsorship(Model model) throws ReportException {
        try {
            List<Student> awaitingSponsorship = reportService.getStudentsBySponsorshipType(SponsorshipType.AwaitingSponsorship);
            model.addAttribute("students", awaitingSponsorship);
            model.addAttribute("activeTab", "reportA");
            return "/reports/awaitingSponsorship";
        } catch (Exception e) {
            model.addAttribute("error", new ReportException());
            LOG.error("Something bad:", e);
        }
        return "/reports/list";
    }

    @RequestMapping(value = "/report/awaitingSponsorship/bank/{bankName}", method = RequestMethod.GET)
    public String showOnlyAwaitingStudentsWithBank(@PathVariable String bankName, Model model) {
        List<Student> students = studentService.getAllStudentsWithBank(SponsorshipType.AwaitingSponsorship, bankName);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportA");
        return "/reports/awaitingSponsorship";
    }

    @RequestMapping(value = "/report/awaitingSponsorship/school", method = RequestMethod.GET)
    public String showOnlySchoolStudentsAwaitingSponsorship(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.AwaitingSponsorship, InstitutionType.School);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportA");
        return "/reports/awaitingSponsorship";
    }

    @RequestMapping(value = "/report/awaitingSponsorship/university", method = RequestMethod.GET)
    public String showOnlyUniveristyStudentsAwaitingSponsorship(Model model) {
        List<Student> students = studentService.getAllStudentsByInstitutionType(SponsorshipType.AwaitingSponsorship, InstitutionType.University);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportA");
        return "/reports/awaitingSponsorship";
    }

    @RequestMapping(value="/report/awaitingSponsorship/downloadCsv")
    public void downloadCsvForStudentsAwaitingSponsorship(HttpServletResponse response) throws IOException, ReportException {
        List<Student> listOfStudents = reportService.getStudentsBySponsorshipType(SponsorshipType.AwaitingSponsorship);
        writerService.writeCsvFile("studentsAwaitingSponsorship.csv", AWAITING_SPONRSORSHIP_HEADER_ROW, listOfStudents, response, true, false);
    }
}