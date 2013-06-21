package net.helloworld.site.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Date: 15/03/13
 * Time: 8:01 AM
 */

@Controller
public class UserController {

    @RequestMapping(value="/admin/userHome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal) {
        return setPageMessages(model, principal, true);
    }

    @RequestMapping(value="/member/userHome", method = RequestMethod.GET)
    public String printWelcomeMember(ModelMap model, Principal principal) {
        return setPageMessages(model, principal, false);
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
        return "/user/userPage";
    }
}
