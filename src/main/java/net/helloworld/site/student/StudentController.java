package net.helloworld.site.student;

import net.helloworld.InstitutionType;
import net.helloworld.model.Address;
import net.helloworld.model.Student;
import net.helloworld.service.AddressService;
import net.helloworld.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value="/student", method= RequestMethod.GET)
    public String showStudents() {
        return "/student/students";
    }

    @RequestMapping(value="/student/add", method = RequestMethod.GET)
    public String addStudentPage(Model model) {
        model.addAttribute("activeTab", "education");
        model.addAttribute("student", new Student());
        model.addAttribute("address", new Address());
        model.addAttribute("enums", InstitutionType.values());
        return "/student/addOrUpdate";
    }

    @RequestMapping(value="/student/add", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute Student student, Model model) {
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

    @RequestMapping(value="/student/view", method=RequestMethod.GET)
    public String listAllStudents(Model model) {
        List<Student> allStudents = studentService.getAllStudents();
        model.addAttribute("students", allStudents);
        return "/student/view";
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