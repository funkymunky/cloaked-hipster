package net.helloworld.site.sponsor;

import net.helloworld.data.SponsorDTO;
import net.helloworld.model.Sponsor;
import net.helloworld.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Date: 27/10/13
 * Time: 6:28 PM
 */
@Controller
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @InitBinder("sponsor")
    private void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd//MM/yyyy");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/sponsor/list", method = RequestMethod.GET)
    public String listAllSponsors(Model model) {
        List<SponsorDTO> allSponsors = sponsorService.getAllSponsors();
        model.addAttribute("sponsors", allSponsors);
        return "/sponsor/list";
    }

    @RequestMapping(value = "/sponsor/add", method = RequestMethod.GET)
    public String addSponsorPage(Model model) {
        model.addAttribute("updateMode", false);
        model.addAttribute("sponsor", new Sponsor());
        return "/sponsor/addOrUpdate";
    }

    @RequestMapping(value = "/sponsor/add", method = RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute Sponsor sponsor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/sponsor/addOrUpdate";
        }
        sponsorService.addSponsor(sponsor);

        model.addAttribute("message", "Successfully saved sponsor: " + sponsor.toString());
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", false);
        return "/sponsor/addOrUpdate";
    }

    @RequestMapping(value = "/sponsor/edit/{id}", method = RequestMethod.GET)
    public String editSponsor(@PathVariable int id, Model model) {
        Sponsor sponsor = sponsorService.getSponsor(id);

        model.addAttribute("sponsor", sponsor);
        model.addAttribute("updateMode", true);
        return "/sponsor/addOrUpdate";
    }

    @RequestMapping(value = "sponsor/edit/{id}", method = RequestMethod.POST)
    public String updateSponsor(@ModelAttribute Sponsor sponsor, Model model) {
        sponsorService.updateSponsor(sponsor);
        String message = String.format("%s, %s's record was updated successfully.", sponsor.getLastName().toUpperCase(), sponsor.getFirstName());

        model.addAttribute("message", message);
        model.addAttribute("showLink", true);
        model.addAttribute("updateMode", true);
        return "/sponsor/addOrUpdate";
    }

}