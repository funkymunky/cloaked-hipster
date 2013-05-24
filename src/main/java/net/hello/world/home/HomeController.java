package net.hello.world.home;

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
    public ModelAndView helloWorld() {
        String message = "<br>Hello World!";
        return new ModelAndView("welcome", "message", message);
    }


}
