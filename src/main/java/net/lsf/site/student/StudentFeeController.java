package net.lsf.site.student;

import net.lsf.*;
import net.lsf.data.StudentFeeDTO;
import net.lsf.service.SponsorService;
import net.lsf.service.SponsorshipService;
import net.lsf.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class StudentFeeController {

    Logger log = Logger.getLogger(StudentFeeController.class);

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

    @RequestMapping(value="/student/studentfees/save", method= RequestMethod.POST)
    public String submitForm( @RequestParam(value = "studentid") String studentId,
                              @ModelAttribute("studentFee") StudentFeeDTO studentFee,
                              Model model) throws ParseException {

        String message = null;
        int id = Integer.parseInt(studentId);

//        log.info("The fee I am about to save is: " + studentFee.getAmountOutstanding());
//        log.info("The bank fee I fetched was:" + studentFee.getBankFee());
//        log.info("The exchange rate is: " + studentFee.getExchangeRate());

        studentService.updateStudentSponsorFees(studentFee, id);

        model.addAttribute("student", studentService.getStudent(id));
        model.addAttribute("currencyTypeValues", CurrencyType.values());
        model.addAttribute("activeTab", "studentfee");
        model.addAttribute("message", message);
        model.addAttribute("updateMode", true);
        model.addAttribute("listOfSponsors", sponsorService.getAllSponsors());
        model.addAttribute("sponsorship", studentService.getSponsorshipForStudent(id));
        model.addAttribute("sponsorshipTypeValues", SponsorshipType.values());
        model.addAttribute("institutionTypeValues", InstitutionType.values());
        model.addAttribute("bankValues", BankInstitution.values());
        model.addAttribute("agentTypeValues", AgentType.values());
        model.addAttribute("maxUploadSize", studentService.getMaxUploadFileSize());
        model.addAttribute("studentFee", studentService.getStudentSponsorFees(id));

        return "/student/addOrUpdate";
    }

}
