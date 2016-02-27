package net.lsf.site.student;

import net.lsf.AgentType;
import net.lsf.BankInstitution;
import net.lsf.InstitutionType;
import net.lsf.SponsorshipType;
import net.lsf.model.Address;
import net.lsf.model.Education;
import net.lsf.model.Student;
import net.lsf.service.SponsorService;
import net.lsf.service.StudentService;
import net.lsf.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Date: 27/10/13
 * Time: 6:28 PM
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private StudentValidator studentValidator;

    @InitBinder("student")
    private void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd//MM/yyyy");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator(studentValidator);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String showStudents() {
        return "/student/students";
    }

    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public String listAllStudents(Model model) {
        List<Student> allStudents = studentService.getAllStudents();
        model.addAttribute("maxUploadSize", studentService.getMaxUploadFileSize());
        model.addAttribute("students", allStudents);
        return "/student/list";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    public String addStudentPage(Model model) {
        model.addAttribute("updateMode", false);
        model.addAttribute("student", new Student());
        model.addAttribute("institutionTypeValues", InstitutionType.values());
        model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
        model.addAttribute("bankValues", BankInstitution.values());
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
        model.addAttribute("maxUploadSize", studentService.getMaxUploadFileSize());
        return "/student/addOrUpdate";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("address", new Address());
            model.addAttribute("education", new Education());
            return "/student/addOrUpdate";
        }
        studentService.addStudent(student);

        model.addAttribute("message", "Successfully saved student: " + student.toString());
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", false);
        model.addAttribute("institutionTypeValues", InstitutionType.values());
        model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
        model.addAttribute("bankValues", BankInstitution.values());
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
        model.addAttribute("maxUploadSize", studentService.getMaxUploadFileSize());
        return "/student/addOrUpdate";
    }

    @RequestMapping(value = "/student/edit/{id}", method = RequestMethod.GET)
    public String editStudent(@PathVariable int id, Model model) {
        Student student = studentService.getStudent(id);

        model.addAttribute("student", student);
        model.addAttribute("updateMode", true);
        model.addAttribute("institutionTypeValues", InstitutionType.values());
        model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
        model.addAttribute("bankValues", BankInstitution.values());
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
        model.addAttribute("maxUploadSize", studentService.getMaxUploadFileSize());
        model.addAttribute("applicationDate", studentService.getApplicationDateForStudent(id));
        return "/student/addOrUpdate";
    }

    @RequestMapping(value = "/student/edit/{id}", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute Student student, Model model) {
        studentService.updateStudent(student);
        String message = String.format("%s, %s's record was updated successfully.", student.getLastName().toUpperCase(), student.getFirstName());

        model.addAttribute("message", message);
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", true);
        model.addAttribute("institutionTypeValues", InstitutionType.values());
        model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
        model.addAttribute("bankValues", BankInstitution.values());
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
        model.addAttribute("maxUploadSize", studentService.getMaxUploadFileSize());
        return "/student/addOrUpdate";
    }

    @RequestMapping(value = "/student/search/{searchText}", method= RequestMethod.GET)
    public String searchStudent(@PathVariable String searchText, Model model) {
        Set<Student> studentByNameOrStandingOrder = studentService.getStudentByNameOrStandingOrderOrAccountNumber(searchText);

        model.addAttribute("students", studentByNameOrStandingOrder);
        model.addAttribute("showAllButton", true);
        return "/student/list";
    }

}