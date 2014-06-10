package net.helloworld.site.sponsorship;

import net.helloworld.SponsorshipType;
import net.helloworld.model.Sponsorship;
import net.helloworld.service.SponsorService;
import net.helloworld.service.SponsorshipService;
import net.helloworld.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SponsorshipController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SponsorshipService sponsorshipService;

    @Autowired
    private SponsorService sponsorService;

    @InitBinder("sponsorship")
    private void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value="/sponsorship/addOrUpdate", method= RequestMethod.POST)
    public String submitForm( @RequestParam(value = "studentid") String studentId,
                              @ModelAttribute SponsorshipCommand sponsorshipCommand,
                              Model model) {

        String message = null;
        int id = Integer.parseInt(studentId);
        sponsorshipCommand.setStudentOnSponsorship(studentId);
        Sponsorship currentSponsorship = studentService.getSponsorshipForStudent(id);
        Sponsorship updatedSponsorship = sponsorshipCommand.getSponsorship();

        if (currentSponsorship != updatedSponsorship && currentSponsorship != null) {
            sponsorshipService.updateSponsorship(updatedSponsorship, currentSponsorship.getId());
            message = "Successfully updated sponsorship details";
        } else {
            sponsorshipService.addSponsorhsip(updatedSponsorship);
            studentService.updateSponsorshipForStudent(id, updatedSponsorship);
            message = "Successfully added sponsorship details";
        }

        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
        model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
        model.addAttribute("sponsorship", studentService.getSponsorshipForStudent(id));
        model.addAttribute("activeTab", "sponsor");
        model.addAttribute("message", message);
        model.addAttribute("updateMode", true);

        return "/student/addOrUpdate";
    }

    private static final class SponsorshipCommand {
        private Sponsorship sponsorship;

        public Sponsorship getSponsorship() {
            return sponsorship;
        }

        public void setSponsorship(Sponsorship sponsorship) {
            this.sponsorship = sponsorship;
        }

        public void setStudentOnSponsorship(String id) {
            this.sponsorship.setStudent(Long.valueOf(id));
        }
    }
}
