package group11.EventFiesta.controller.UserEvents;

import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"user"})
@Controller
public class UserHomeController {

    @GetMapping("/userHome")
    public String getUserHomePage(@ModelAttribute User user, Model model)
    {
        model.addAttribute("user", user);
        return "UserHomepage";
    }

}
