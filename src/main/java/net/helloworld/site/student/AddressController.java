package net.helloworld.site.student;

import net.helloworld.model.Address;
import net.helloworld.model.Education;
import net.helloworld.service.AddressService;
import net.helloworld.service.StudentService;
import org.apache.log4j.Logger;
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

    private static final Logger log = Logger.getLogger(AddressController.class);

    @RequestMapping(value="/address/addOrUpdate", method = RequestMethod.POST)
    public String submitForm( @RequestParam("studentid") String studentId,
                              @ModelAttribute AddressCommand addressCommand,
                              Model model) {

        String message;
        int id = Integer.parseInt(studentId);
        Education education = studentService.getEducationForStudent(id);

        Address currentAddress = studentService.getAddressForStudent(id);
        Address updatedAddress = addressCommand.getAddress();

        if (currentAddress != updatedAddress && currentAddress != null) {
            addressService.updateAddress(updatedAddress, currentAddress.getId());
            message = "Successfully updated address";
        } else {
            addressService.addAddress(updatedAddress);
            studentService.updateAddressForStudent(id, updatedAddress);
            message = "Successfully added address ";
        }

        model.addAttribute("activeTab", "address");
        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("message", message);
        model.addAttribute("education", education);
        model.addAttribute("updateMode", true);
        return "/student/addOrUpdate";
    }

 private static class AddressCommand {
     private Address address;

     public Address getAddress() {
         return address;
     }

     public void setAddress(Address address) {
         this.address = address;
     }
 }
}