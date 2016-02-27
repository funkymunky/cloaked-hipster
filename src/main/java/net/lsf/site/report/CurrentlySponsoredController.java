package net.lsf.site.report;

import net.lsf.BankInstitution;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Student;
import net.lsf.service.ReportService;
import net.lsf.service.SponsorService;
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
import java.util.Map;

@Controller
public class CurrentlySponsoredController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private WriterService writerService;

    private static final String[] CURENTLY_SPONSORED_HEADER_ROW = {"Student id", "Student name", "Year of study", "School / University", "Sponsor", "Account name", "Bank", "Branch", "Account number", "Standing order number"};

    @RequestMapping(value = "/report/currentlySponsored", method = RequestMethod.GET)
    public String showCurrentlySponsoredStudents(Model model) throws ReportException {
        List<Student> sponsored = reportService.getStudentsBySponsorshipType(SponsorshipType.CurrentlySponsored);
        Map<String, String> sponsors =  sponsorService.getMapOfAllSponsors();
        model.addAttribute("students", sponsored);
        model.addAttribute("activeTab", "reportC");
        model.addAttribute("allSponsors", sponsors);
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value = "/report/currentlySponsored/school", method = RequestMethod.GET)
    public String showOnlySchoolStudents(Model model) throws ReportException {
        List<Student> students = reportService.getStudentsByInstitutionType(SponsorshipType.CurrentlySponsored, InstitutionType.School);
        Map<String, String> sponsors =  sponsorService.getMapOfAllSponsors();

        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportC");
        model.addAttribute("allSponsors", sponsors);
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value = "/report/currentlySponsored/university", method = RequestMethod.GET)
    public String showOnlyUniversityStudents(Model model) throws ReportException {
        List<Student> students = reportService.getStudentsByInstitutionType(SponsorshipType.CurrentlySponsored, InstitutionType.University);
        Map<String, String> sponsors =  sponsorService.getMapOfAllSponsors();

        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportC");
        model.addAttribute("allSponsors", sponsors);
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value="/report/currentlySponsored/bank/{bankName}", method = RequestMethod.GET)
    public String showOnlySponsoredStudentsWithBank(@PathVariable String bankName, Model model) throws ReportException {
        BankInstitution bankInstitution = getInstitutionFromString(bankName);
        List<Student> students = reportService.getStudentsByBank(SponsorshipType.CurrentlySponsored, bankInstitution);
        Map<String, String> sponsors =  sponsorService.getMapOfAllSponsors();

        model.addAttribute("students", students);
        model.addAttribute("activeTab", "reportC");
        model.addAttribute("allSponsors", sponsors);
        return "/reports/currentlySponsored";
    }

    @RequestMapping(value="/report/currentlySponsored/downloadCsv")
    public void downloadCsvForCurrentlySponsoredStudents(HttpServletResponse response) throws IOException, ReportException {
        List<Student> listOfStudents = reportService.getStudentsBySponsorshipType(SponsorshipType.CurrentlySponsored);
        writerService.writeCsvFile("currentlySponsoredStudents.csv", CURENTLY_SPONSORED_HEADER_ROW, listOfStudents, response, false, true);
    }

    private BankInstitution getInstitutionFromString(String bankName) {
        for (BankInstitution institution : BankInstitution.values()) {
            if (institution.getDescription().toLowerCase().contains(bankName.trim().toLowerCase())) {
                return institution;
            }
        }
        return BankInstitution.Default;
    }
}
