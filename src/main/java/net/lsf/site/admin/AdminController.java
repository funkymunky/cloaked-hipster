package net.lsf.site.admin;

import net.lsf.service.HashCodeService;
import net.lsf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    HashCodeService hashCodeService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/resetPassword", method = RequestMethod.GET)
    public String showResetPasswordForm(Model model) {
        model.addAttribute("security", new SecurityCommand());
        return "/admin/resetPassword";
    }

    @RequestMapping(value = "/admin/resetPassword", method = RequestMethod.POST)
    public String saveNewPassword(@ModelAttribute SecurityCommand securityCommand, Model model, Principal principal) {

        String encryptedPassword = hashCodeService.getHashPassword(securityCommand.getNewPassword());
        String name = principal.getName();
        userService.updateUserPassword(name, encryptedPassword);
        model.addAttribute("security", securityCommand);
        return "/admin/resetPassword";
    }

    private static class SecurityCommand {
        String newPassword;

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
}
