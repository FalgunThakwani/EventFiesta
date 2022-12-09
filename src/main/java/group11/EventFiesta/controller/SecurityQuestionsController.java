package group11.EventFiesta.controller;

import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityQuestionsController {
    @GetMapping("/securityQuestion")
    public String getSecretQuestionPage() {
        return "SecurityQuestion";
    }

    @PostMapping("/handleSecurityQuestion")
    public String handleSecretQuestion(@ModelAttribute User user) {
        // Todo: logic for checking username and password

        return "redirect:/resetPassword";
    }
}
