package net.lsf.site.report;

import net.lsf.BankInstitution;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Student;
import net.lsf.service.ReportService;
import net.lsf.service.WriterService;
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
public class ExpiredApplicationController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private WriterService writerService;

    @RequestMapping(value = "/report/applicationExpired", method = RequestMethod.GET)
    public String showStudentsWhereApplicationExpired(Model model) throws ReportException {
        List<Student> students = reportService.getStudentsBySponsorshipType(SponsorshipType.ApplicationExpired);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value = "/report/applicationExpired/school", method = RequestMethod.GET)
    public String showOnlySchoolStudentsWhereApplicationExpired(Model model) throws ReportException {
        List<Student> students = reportService.getStudentsByInstitutionType(SponsorshipType.ApplicationExpired, InstitutionType.School);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        model.addAttribute("activeFilter", "school");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value = "/report/applicationExpired/university", method = RequestMethod.GET)
    public String showOnlyUniveristyStudentsWhereApplicationExpired(Model model) throws ReportException {
        List<Student> students = reportService.getStudentsByInstitutionType(SponsorshipType.ApplicationExpired, InstitutionType.University);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        model.addAttribute("activeFilter", "uni");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value="/report/applicationExpired/bank/{bankName}", method = RequestMethod.GET)
    public String showOnlyApplicationExpiredWithBank(@PathVariable String bankName, Model model) throws ReportException {
        BankInstitution bankInstitution = BankInstitution.getInstitutionFromString(bankName);
        List<Student> students = reportService.getStudentsByBank(SponsorshipType.ApplicationExpired, bankInstitution);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportAE");
        model.addAttribute("activeFilter", "bank");
        return "/reports/applicationExpired";
    }

    @RequestMapping(value="/report/applicationExpired/downloadCsv")
    public void downloadCsvForStudentsWhereApplicationExpired(HttpServletResponse response) throws IOException, ReportException {
        List<Student> listOfStudents = reportService.getStudentsBySponsorshipType(SponsorshipType.ApplicationExpired);
        writerService.writeCsvFile("studentsApplicationExpired.csv", ReportController.HEADER_ROW, listOfStudents, response, false, false);
    }

}
