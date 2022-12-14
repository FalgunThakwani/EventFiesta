package group11.EventFiesta.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/contactUs")
    public String getContactUs() {
        return "contactUs";
    }

    @GetMapping("/aboutUs")
    public String getAboutUs() {
        return "aboutUs";
    }

    @GetMapping("/ourServices")
    public String getOurServices() {
        return "ourServices";
    }

}
