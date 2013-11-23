package net.helloworld.site.student;

import net.helloworld.InstitutionType;
import net.helloworld.model.Address;
import net.helloworld.model.Student;
import net.helloworld.service.AddressService;
import net.helloworld.service.StudentService;
import net.helloworld.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Date: 27/10/13
 * Time: 6:28 PM
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private StudentValidator studentValidator;

    @InitBinder("student")
    private void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd//MM/yyyy");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.setValidator(studentValidator);
    }

    @RequestMapping(value="/student", method= RequestMethod.GET)
    public String showStudents() {
        return "/student/students";
    }

    @RequestMapping(value="/student/list", method=RequestMethod.GET)
    public String listAllStudents(Model model) {
        List<Student> allStudents = studentService.getAllStudents();
        model.addAttribute("students", allStudents);
        return "/student/list";
    }

    @RequestMapping(value="/student/add", method = RequestMethod.GET)
    public String addStudentPage(Model model) {
        model.addAttribute("activeTab", "education");
        model.addAttribute("updateMode", false);
        model.addAttribute("student", new Student());
        model.addAttribute("address", new Address());
        model.addAttribute("enums", InstitutionType.values());
        return "/student/addOrUpdate";
    }

    @RequestMapping(value="/student/add", method = RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("address", new Address());
            model.addAttribute("activeTab", "education");
            return "/student/addOrUpdate";
        }
        studentService.addStudent(student);
        Address address = getAddress(student.getId());

        model.addAttribute("activeTab", "education");
        model.addAttribute("message", "Successfully saved student: " + student.toString());
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", false);
        model.addAttribute("address", address);
        model.addAttribute("enums", InstitutionType.values());
        return "/student/addOrUpdate";
    }

    @RequestMapping(value="/student/edit/{id}", method=RequestMethod.GET)
    public String editStudent(@PathVariable int id, Model model) {
        Student student = studentService.getStudent(id);
        Address address = getAddress(id);

        model.addAttribute("activeTab", "education");
        model.addAttribute("student", student);
        model.addAttribute("updateMode", true);
        model.addAttribute("address", address);
        model.addAttribute("enums", InstitutionType.values());
        return "/student/addOrUpdate";
    }


    @RequestMapping(value="student/edit/{id}", method=RequestMethod.POST)
    public String updateStudent(@ModelAttribute Student student, Model model) {
        studentService.updateStudent(student);
        Address address = getAddress(student.getId());
        String message = String.format("%s, %s's record was updated successfully.", student.getLastName().toUpperCase(), student.getFirstName());

        model.addAttribute("activeTab", "education");
        model.addAttribute("message", message);
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", true);
        model.addAttribute("address", address);
        model.addAttribute("enums", InstitutionType.values());
        return "/student/addOrUpdate";
    }

    private Address getAddress(int id) {
        Address address = studentService.getAddressForStudent(id);
        return address == null ? new Address() : address;
    }
}