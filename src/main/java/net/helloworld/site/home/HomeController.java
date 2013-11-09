package net.helloworld.site.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Ayesha
 * Date: 15/03/13
 * Time: 8:00 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHomePage() {
        String message = "<br>Hello World!";
//        return new ModelAndView("login", "message", message);
        return "hello";
    }


}
