package group11.EventFiesta.controller;

import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForgotPasswordController {
    @GetMapping("/forgotPassword")
    public String getForgotPasswordPage(Model model)
    {
        model.addAttribute("user", new User());
        return "UserForgotPassword";
    }

    @PostMapping("/handleForgotPasswordWithSecurityQuestion")
    public String handleForgotPasswordUsingSecurityQuestion(Model model)
    {
        return "redirect:/securityQuestion";
    }
}
