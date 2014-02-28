package net.helloworld.site.student;

import net.helloworld.model.Address;
import net.helloworld.model.Education;
import net.helloworld.model.Student;
import net.helloworld.service.EducationService;
import net.helloworld.service.StudentService;
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

    private Logger log = Logger.getLogger(EducationController.class);



    @InitBinder("education")
    private void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, "applicationDate", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "endDate", new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value="/education/addOrUpdate", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute Education education, @RequestParam(value="studentid") String studentId, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString());

        }  else {
            int id = Integer.parseInt(studentId);
            Student student = studentService.getStudent(id);
            Address address = studentService.getAddressForStudent(id);

            String message;

            if (student.getEducation() != null) {
                Education oldEducation = studentService.getEducationForStudent(id);
                educationService.updateEducation(education, oldEducation.getId());
                message = "Successfully updated education details";
            } else {
                educationService.addEducation(education);
                studentService.updateEducationForStudent(id, education);
                message = "Successfully added education details ";
            }

            model.addAttribute("activeTab", "education");
            model.addAttribute("student", student);
            model.addAttribute("address", address);
            model.addAttribute("message", message);
            model.addAttribute("updateMode", true);
        }
        return "/student/addOrUpdate";
    }
}