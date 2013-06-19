package net.helloworld.site;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Ayesha
 * Date: 11/05/13
 * Time: 8:05 AM
 * To change this template use File | Settings | File Templates.
 */

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
public class LoginController {

    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal) {

        String name = principal.getName();
        model.put("username", name);
        model.put("message", "Welcome back " + name);
        return "hello";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "login";

    }

    @RequestMapping(value="/loginFail", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

//        model.addAttribute("error", "true");
        return "loginFail";

    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "login";
    }

}
