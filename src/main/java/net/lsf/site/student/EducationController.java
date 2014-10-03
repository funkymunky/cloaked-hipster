package net.lsf.site.student;

import net.lsf.AgentType;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Education;
import net.lsf.model.Student;
import net.lsf.service.EducationService;
import net.lsf.service.SponsorService;
import net.lsf.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class EducationController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SponsorService sponsorService;

    private Logger log = Logger.getLogger(EducationController.class);



    @InitBinder("education")
    private void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, "applicationDate", new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value="/student/education/addOrUpdate", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute EducationCommand education,
                             @RequestParam(value="studentid") String studentId,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString());

        }  else {
            String message;
            int id = Integer.parseInt(studentId);
            Student student = studentService.getStudent(id);

            Education currentEducation = student.getEducation();
            Education modifiedEducation = education.getEducation();

            if (currentEducation != modifiedEducation && currentEducation != null) {
                educationService.updateEducation(modifiedEducation, currentEducation.getId());
                message = "Successfully updated education details";
            } else {
                educationService.addEducation(modifiedEducation);
                studentService.updateEducationForStudent(id, modifiedEducation);
                message = "Successfully added education details ";
            }

            model.addAttribute("activeTab", "education");
            model.addAttribute("student", studentService.getStudent(id));
            model.addAttribute("institutionTypeValues", InstitutionType.values());
            model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
            model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
            model.addAttribute("agentTypeValues", AgentType.values());
            model.addAttribute("message", message);
            model.addAttribute("updateMode", true);
        }
        return "/student/addOrUpdate";
    }

    public static final class EducationCommand {
        Education education;

        public Education getEducation() {
            return this.education;
        }

        public void setEducation(Education education) {
            this.education = education;
        }

    }
}