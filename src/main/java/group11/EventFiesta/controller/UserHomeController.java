package group11.EventFiesta.controller;

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
        System.out.println("Inside UserHomeController user: " + user.toString());

        System.out.println("Logged in user ........." + user.getFirstName());

        user.setFirstName("Demo");
        user.setLastName("User");

        model.addAttribute("user", user);

        return "UserHomepage";
    }


}
