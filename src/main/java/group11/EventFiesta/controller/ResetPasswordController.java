package group11.EventFiesta.controller;

import group11.EventFiesta.ForgotPassword.ResetPasswordHandler;
import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"user"})
@Controller
public class ResetPasswordController {
    @GetMapping("/resetPassword")
    public String getResetPasswordPage(Model model, @ModelAttribute User user)
    {
        model.addAttribute("user", user);
        return "ResetPassword";
    }

    @PostMapping("/handleResetPassword")
    public String handleResetPassword(Model model, @ModelAttribute User user) throws Exception {
        model.addAttribute("user", user);
        System.out.println("user id is : " + user.getUserId());
        System.out.println("user password is : " + user.getPassword());
        System.out.println("user confirm password is : " + user.getConfirmPassword());
        if(user.getPassword().equals(user.getConfirmPassword()))
        {
            ResetPasswordHandler resetPasswordHandler = new ResetPasswordHandler();
            boolean result = resetPasswordHandler.resetPassword(user.getUserId(), user.getPassword());
            if(result == true)
            {
                model.addAttribute("statusMsg", "PASSWORDS UPDATED SUCCESSFULLY");
                return "ResetPassword";
            }
            else
            {
                model.addAttribute("statusMsg", "PASSWORDS NOT UPDATED");
                return "ResetPassword";
            }
        }
        else
        {
            model.addAttribute("statusMsg", "PASSWORDS NOT MATCHING");
            return "ResetPassword";
        }
    }
}
