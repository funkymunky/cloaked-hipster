package net.helloworld.site.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal) {
        return setPageMessages(model, principal, true); // todo workout how to distinguish between user types
    }

    private String setPageMessages(ModelMap model, Principal principal, boolean adminMember) {
        String name = principal.getName();
        String memberType = "member";
        String role = "ROLE_MEMBER";

        if (adminMember) {
            memberType = "admin";
            role = "ROLE_ADMIN";
        }

        model.put("username", name);
        model.put("role", role);
        String messageToDisplay = String.format("Welcome back %s.\nYou are on the %s page.", name, memberType);
        model.put("message", messageToDisplay);
        return "/hello";
    }

}
