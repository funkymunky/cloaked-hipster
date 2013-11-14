package net.helloworld.site.student;

import net.helloworld.model.Address;
import net.helloworld.model.Student;
import net.helloworld.service.AddressService;
import net.helloworld.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/address/addOrUpdate", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute Address address, @RequestParam("studentid") String studentId, Model model) {

        int id = Integer.parseInt(studentId);
        Student student = studentService.getStudent(id);

        String message;

        if (student.getAddress() != null) {
            Address oldAddress = studentService.getAddressForStudent(id);
            addressService.updateAddress(address, oldAddress.getId());
            message = "Successfully updated address";
        } else {
            addressService.addAddress(address);
            studentService.updateAddressForStudent(id, address);
            message = "Successfully added address ";
        }

        model.addAttribute("activeTab", "address");
        model.addAttribute("student", student);
        model.addAttribute("message", message);
        return "/student/addOrUpdate";
    }
}