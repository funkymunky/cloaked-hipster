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
public class FormerlySponsoredController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private WriterService writerService;

    @RequestMapping(value = "/report/formerlySponsored", method = RequestMethod.GET)
    public String showStudentsFormerlySponsored(Model model) throws ReportException {
        List<Student> formerlySponsored = reportService.getStudentsBySponsorshipType(SponsorshipType.FormerlySponsored);
        model.addAttribute("students", formerlySponsored);
        model.addAttribute("activeTab", "reportF");
        return "/reports/formerlySponsored";
    }

    @RequestMapping(value = "/report/formerlySponsored/school", method = RequestMethod.GET)
    public String showOnlySchoolStudentsFormerlySponsored(Model model) throws ReportException {
        List<Student> students = reportService.getStudentsByInstitutionType(SponsorshipType.FormerlySponsored, InstitutionType.School);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportF");
        model.addAttribute("activeFilter", "school");
        return "/reports/formerlySponsored";
    }

    @RequestMapping(value = "/report/formerlySponsored/university", method = RequestMethod.GET)
    public String showOnlyUniveristyStudentsFormerlySponsored(Model model) throws ReportException {
        List<Student> students = reportService.getStudentsByInstitutionType(SponsorshipType.FormerlySponsored, InstitutionType.University);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportF");
        model.addAttribute("activeFilter", "uni");
        return "/reports/formerlySponsored";
    }

    @RequestMapping(value="/report/formerlySponsored/bank/{bankName}", method = RequestMethod.GET)
    public String showOnlyFormerlySponsoredWithBank(@PathVariable String bankName, Model model) throws ReportException {
        BankInstitution bankInstitution = BankInstitution.getInstitutionFromString(bankName);
        List<Student> students = reportService.getStudentsByBank(SponsorshipType.FormerlySponsored, bankInstitution);
        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportF");
        model.addAttribute("activeFilter", "bank");
        return "/reports/formerlySponsored";
    }

    @RequestMapping(value="/report/formerlySponsored/downloadCsv")
    public void downloadCsvForStudentsFormerlySponsored(HttpServletResponse response) throws IOException, ReportException {
        List<Student> listOfStudents = reportService.getStudentsBySponsorshipType(SponsorshipType.FormerlySponsored);
        writerService.writeCsvFile("studentsFormerlySponsored.csv", ReportController.HEADER_ROW, listOfStudents, response, false, false);
    }

}
