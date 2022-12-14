package group11.EventFiesta.controller.forgotpassword;

import group11.EventFiesta.model.Organizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrganizerForgotPasswordController {
    @GetMapping("/organizerForgotPassword")
    public String getForgotPasswordPage(Model model)
    {
        model.addAttribute("organizer", new Organizer());
        return "OrganizerForgotPassword";
    }

    @PostMapping("/handleOrganizerForgotPasswordWithSecurityQuestion")
    public String handleOrganizerForgotPasswordUsingSecurityQuestion(Model model)
    {
        return "redirect:/organizerSecurityQuestion";
    }
}
