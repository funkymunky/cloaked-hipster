package net.helloworld.site.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView findAllAccounts() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("account");
        mav.addObject("someText", "Listing all accounts!");
        return mav;
    }

    @RequestMapping(value="/account/{accountId}", method = RequestMethod.GET)
    public ModelAndView findAccount(@PathVariable int accountId, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("account");
        mav.addObject("someText", String.format("Showing account %d", accountId));
        return mav;
    }
}