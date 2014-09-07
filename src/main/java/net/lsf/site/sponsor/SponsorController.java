package net.lsf.site.sponsor;

import net.lsf.data.SponsorDTO;
import net.lsf.model.Sponsor;
import net.lsf.model.SponsorshipFees;
import net.lsf.model.Student;
import net.lsf.service.FeesService;
import net.lsf.service.SponsorService;
import net.lsf.service.SponsorshipFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Date: 27/10/13
 * Time: 6:28 PM
 */
@Controller
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private FeesService feesService;

    @Autowired
    private SponsorshipFeesService sponsorshipFeesService;

    @InitBinder("sponsor")
    private void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd//MM/yyyy");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/sponsor/list", method = RequestMethod.GET)
    public String listAllSponsors(Model model) {
        List<SponsorDTO> allSponsors = sponsorService.getAllSponsors();
        model.addAttribute("sponsors", allSponsors);
        return "/sponsor/list";
    }

    @RequestMapping(value = "/sponsor/add", method = RequestMethod.GET)
    public String addSponsorPage(Model model) {
        model.addAttribute("updateMode", false);
        model.addAttribute("sponsor", new Sponsor());
        return "/sponsor/addOrUpdate";
    }

    @RequestMapping(value = "/sponsor/add", method = RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute Sponsor sponsor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/sponsor/addOrUpdate";
        }
        sponsorService.addSponsor(sponsor);

        model.addAttribute("message", "Successfully saved sponsor: " + sponsor.toString());
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", false);
        return "/sponsor/addOrUpdate";
    }

    @RequestMapping(value = "/sponsor/edit/{id}", method = RequestMethod.GET)
    public String editSponsor(@PathVariable int id, Model model) {
        Sponsor sponsor = sponsorService.getSponsor(id);

        model.addAttribute("sponsor", sponsor);
        model.addAttribute("updateMode", true);
        prepareModelWithSummaryInfo(id, model);
        return "/sponsor/addOrUpdate";
    }

    @RequestMapping(value = "/sponsor/edit/{id}", method = RequestMethod.POST)
    public String updateSponsor(@ModelAttribute Sponsor sponsor, Model model) {
        sponsorService.updateSponsor(sponsor);
        String message = String.format("%s, %s's record was updated successfully.", sponsor.getLastName().toUpperCase(), sponsor.getFirstName());

        model.addAttribute("message", message);
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", true);
        prepareModelWithSummaryInfo(sponsor.getId(), model);
        return "/sponsor/addOrUpdate";
    }

    @RequestMapping(value = "/sponsor/search/{searchText}", method= RequestMethod.GET)
    public String searchSponsor(@PathVariable String searchText,
                                Model model) {
        Set<SponsorDTO> sponsorsByName = sponsorService.getSponsorByName(searchText);

        model.addAttribute("sponsors", sponsorsByName);
        model.addAttribute("showAllButton", true);
        return "/sponsor/list";
    }

    private void prepareModelWithSummaryInfo(int id, Model model) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<Student> sponsoredKids = sponsorService.getAllSponsoredKids(id);

        model.addAttribute("sponsoredKids", sponsoredKids);
        Date issueDate = feesService.getCurrentFees().getIssueDate();
        model.addAttribute("feeDueDate", issueDate == null ? "" : df.format(issueDate));
        model.addAttribute("totalFees", calculateTotalFees(sponsoredKids));
        model.addAttribute("feesPerStudent", prepareFeesForStudents(id, sponsoredKids));
    }

    private String calculateTotalFees(List<Student> students) {
        BigDecimal totalAllowance = BigDecimal.ZERO;
        for (Student student : students) {
            BigDecimal monthlyAllowance = student.getEducation().getMonthlyAllowance();
            if (monthlyAllowance == null) {
                monthlyAllowance = BigDecimal.ZERO;
            }
            totalAllowance = totalAllowance.add(monthlyAllowance);
        }
        totalAllowance.setScale(2, RoundingMode.UP);
        Locale locale = new Locale("en", "AU");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(totalAllowance);
    }

    private List<SponsorshipFees> prepareFeesForStudents(int sponsorId, List<Student> students) {
        List<SponsorshipFees> sponsorFees = sponsorshipFeesService.getFeesForSponsor(sponsorId);
        if (sponsorFees.size() != students.size()) {
            for (Student student : students) {
                if (!feeForStudentExists(sponsorFees, student.getId())) {
                    SponsorshipFees blankFee = new SponsorshipFees(null, null, null, student, null);
                    sponsorFees.add(blankFee);
                    break;
                }
            }
        }

        return sponsorFees;
    }

    private boolean feeForStudentExists(List<SponsorshipFees> sponsorFees, Integer studentId) {
        boolean found = false;
        for (SponsorshipFees fee : sponsorFees) {
            if (fee.getStudent().getId().equals(studentId)) {
                return true;
            }
        }
        return found;
    }

}