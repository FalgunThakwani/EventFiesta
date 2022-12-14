package group11.EventFiesta.controller.forgotpassword;

import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserForgotPasswordController {
    @GetMapping("/userForgotPassword")
    public String getForgotPasswordPage(Model model)
    {
        model.addAttribute("user", new User());
        return "UserForgotPassword";
    }

    @PostMapping("/handleUserForgotPasswordWithSecurityQuestion")
    public String handleUserForgotPasswordUsingSecurityQuestion(Model model)
    {
        return "redirect:/userSecurityQuestion";
    }
}
