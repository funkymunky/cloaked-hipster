package net.helloworld.site.admin;

import net.helloworld.service.FeesService;
import net.helloworld.service.SponsorshipFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private FeesService feesService;

    @Autowired
    private SponsorshipFeesService sponsorshipFeesService;

    @InitBinder("fees")
    private void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(java.sql.Date.class, "issueDate", new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/manage/fees", method = RequestMethod.GET)
    public String showAdminHomePage(Model model) {
        model.addAttribute("fees", feesService.getCurrentFees());
        model.addAttribute("sponsoredStudents", sponsorshipFeesService.getOutstandingFees());
        model.addAttribute("outstandingPayments", new OutstandingPayments());
        return "/manage/fees";
    }

    @RequestMapping(value="/manage/fees", method = RequestMethod.POST)
    public String saveNewFeeIssueDate(@ModelAttribute FeesCommand feesCommand, Model model) {
        feesService.updateFeeIssueDate(feesCommand.getIssueDate());
        model.addAttribute("fees", feesCommand);
        model.addAttribute("sponsoredStudents", sponsorshipFeesService.getOutstandingFees());
        model.addAttribute("outstandingPayments", new OutstandingPayments());
        return "/manage/fees";
    }

    @RequestMapping(value="/manage/payments", method= RequestMethod.POST)
    public String updatePaymentsReceived(@ModelAttribute OutstandingPayments outstandingPayments, Model model) {
        sponsorshipFeesService.updatePaymentsReceived(outstandingPayments.getPaidFees());
        model.addAttribute("fees", feesService.getCurrentFees());
        model.addAttribute("sponsoredStudents", sponsorshipFeesService.getOutstandingFees());
        model.addAttribute("outstandingPayments", new OutstandingPayments());
        return "/manage/fees";
    }

    private static class FeesCommand {
        private Date issueDate;

        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(String issueDate) throws ParseException {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(issueDate);
            this.issueDate = date;
        }
    }

    private static class OutstandingPayments {
        private List<String> paidFees;

        public List<String> getPaidFees() {
            return paidFees;
        }

        public void setPaidFees(List<String> paidFees) {
            this.paidFees = paidFees;
        }
    }
}
