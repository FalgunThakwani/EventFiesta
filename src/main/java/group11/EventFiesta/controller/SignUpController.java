package group11.EventFiesta.controller;

import group11.EventFiesta.model.Organizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import group11.EventFiesta.model.User;

@Controller
public class SignUpController {

    @GetMapping("/signup")
    public String getHomePage(Model model) {
        model.addAttribute("user", new User());
        return "UserSignUp";
    }

    @PostMapping("/handleUserSignUp")
    public String handleUserSignUp(@ModelAttribute User user) {
        // Todo: Store User object in db

        return "UserSignUp";
    }

    @GetMapping("/org/signup")
    public String getOrgSignUpPage(Model model){
        model.addAttribute("organizer", new Organizer());
        return "OrganizerSignUp";
    }

    @PostMapping("/handleOrgSignUp")
    public String handleOrgSignUp(@ModelAttribute Organizer organizer) {
        //Store Organizer object in db

        return "home";
    }

}
