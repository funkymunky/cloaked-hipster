package net.lsf.site.student;

import net.lsf.AgentType;
import net.lsf.BankInstitution;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Bank;
import net.lsf.model.Student;
import net.lsf.service.BankService;
import net.lsf.service.SponsorService;
import net.lsf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SponsorService sponsorService;

    @RequestMapping(value="/student/bank/addOrUpdate", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute BankCommand bank,
                             @RequestParam(value="studentid") String studentId,
                             BindingResult bindingResult,
                             Model model) {
        String message;
        int id = Integer.parseInt(studentId);
        Student student = studentService.getStudent(id);

        Bank currentBank = student.getBank();
        Bank modifiedBank = bank.getBank();

        if (currentBank != modifiedBank && currentBank != null) {
            bankService.updateBank(modifiedBank, currentBank.getId());
            message = "Successfully updated education details";
        } else {
            bankService.addBank(modifiedBank);
            studentService.updateBankForStudent(id, modifiedBank);
            message = "Successfully added banking details ";
        }

        model.addAttribute("activeTab", "bank");
        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("institutionTypeValues", InstitutionType.values());
        model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
        model.addAttribute("bankValues", BankInstitution.values());
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
        model.addAttribute("maxUploadSize", studentService.getMaxUploadFileSize());
        model.addAttribute("message", message);
        model.addAttribute("updateMode", true);

        return "/student/addOrUpdate";
    }

    private static final class BankCommand {
        private Bank bank;

        public Bank getBank() {
            return bank;
        }

        public void setBank(Bank bank) {
            this.bank = bank;
        }
    }
}
