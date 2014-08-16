package net.helloworld.site;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Set;

/**
 * Date: 11/05/13
 * Time: 8:05 AM
 */

@Controller
public class LoginController {

    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal, Authentication authentication) {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            return setPageMessages(model, principal, true);
        } else if (roles.contains("ROLE_MEMBER")) {
            return setPageMessages(model, principal, false);
        }
        return "hello";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "login";

    }

    @RequestMapping(value="/loginFail", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        model.addAttribute("message", "Login failed");
        return "login";

    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "login";
    }

    @RequestMapping(value="/accessDenied", method = RequestMethod.GET)
    public String accessDenied(ModelMap model) {
        return "accessDenied";
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
        String messageToDisplay = String.format("Welcome back %s.\nYou are on the home page.", name);
        model.put("message", messageToDisplay);
        return "/user/home";
    }
}
