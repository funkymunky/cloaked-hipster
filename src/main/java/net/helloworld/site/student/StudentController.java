package net.helloworld.site.student;

import net.helloworld.model.Student;
import net.helloworld.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.AttributedString;
import java.util.List;

/**
 * Date: 27/10/13
 * Time: 6:28 PM
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/student", method= RequestMethod.GET)
    public String showStudents() {
        return "/student/students";
    }

    @RequestMapping(value="/student/add", method = RequestMethod.GET)
    public String addStudentPage(Model model) {
        model.addAttribute("student", new Student());
        return "/student/addOrUpdate";
    }

    @RequestMapping(value="/student/add", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute Student student, Model model) {
        studentService.addStudent(student);
        model.addAttribute("message", "Successfully saved student: " + student.toString());
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", false);
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
        model.addAttribute("student", student);
        model.addAttribute("updateMode", true);
        return "/student/addOrUpdate";
    }

    @RequestMapping(value="student/edit/{id}", method=RequestMethod.POST)
    public String updateStudent(@ModelAttribute Student student, Model model) {
        studentService.updateStudent(student);
        String message = String.format("%s, %s's record was updated successfully.", student.getLastName().toUpperCase(), student.getFirstName());
        model.addAttribute("message", message);
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", true);
        return "/student/addOrUpdate";
    }
}