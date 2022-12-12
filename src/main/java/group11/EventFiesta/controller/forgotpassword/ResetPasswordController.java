package group11.EventFiesta.controller.forgotpassword;

import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResetPasswordController {
    @GetMapping("/resetPassword")
    public String getResetPasswordPage() {
        return "ResetPassword";
    }

    @PostMapping("/handleResetPassword")
    public String handleResetPassword(@ModelAttribute User user) {
        // Todo: logic for checking username and password

        return "home";
    }
}
