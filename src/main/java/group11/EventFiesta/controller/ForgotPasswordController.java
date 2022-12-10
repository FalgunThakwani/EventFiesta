package group11.EventFiesta.controller;

import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPasswordController {
    @GetMapping("/forgotPassword")
    public String getForgotPasswordPage() {
        return "ForgotPassword";
    }

    @PostMapping("/handleForgotPassword")
    public String handleForgotPassword(@ModelAttribute User user) {
        // Todo: logic for checking username and password
        return "redirect:/securityQuestion";
    }
}
