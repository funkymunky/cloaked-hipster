package net.lsf.site.student;

import net.lsf.AgentType;
import net.lsf.BankInstitution;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.service.SponsorService;
import net.lsf.service.SponsorshipService;
import net.lsf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class StudentFeeController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SponsorshipService sponsorshipService;

    @Autowired
    private SponsorService sponsorService;

    @InitBinder("studentfees")
    private void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "endDate", new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value="/student/studentfees/addOrUpdate", method= RequestMethod.POST)
    public String submitForm( @RequestParam(value = "studentid") String studentId,
                              Model model) {

        String message = null;
        int id = Integer.parseInt(studentId);

        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
        model.addAttribute("sponsorship", studentService.getSponsorshipForStudent(id));
        model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
        model.addAttribute("institutionTypeValues", InstitutionType.values());
        model.addAttribute("bankValues", BankInstitution.values());
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("maxUploadSize", studentService.getMaxUploadFileSize());
        model.addAttribute("activeTab", "sponsor");
        model.addAttribute("message", message);
        model.addAttribute("updateMode", true);

        return "/student/addOrUpdate";
    }

}
