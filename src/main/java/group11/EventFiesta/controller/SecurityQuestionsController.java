package group11.EventFiesta.controller;

import group11.EventFiesta.ForgotPassword.SecretQuestionHandler;
import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes({"user"})
@Controller
public class SecurityQuestionsController {
    @GetMapping("/securityQuestion")
    public String getSecretQuestionPage(Model model)
    {
        model.addAttribute("user", new User());
        return "SecurityQuestion";
    }

    @PostMapping("/handleSecurityQuestion")
    public String handleSecretQuestion(Model model, @ModelAttribute User user) throws Exception
    {
        model.addAttribute("user", user);
        System.out.println("Entered email is : "+ user.getEmail());
        System.out.println("Selected security question is : "+ user.getSecurityQuestion());
        System.out.println("Entered security answer is : "+ user.getSecurityAnswer());
        SecretQuestionHandler secretQuestionHandler = new SecretQuestionHandler();
        int user_id = secretQuestionHandler.validateEmail(user.getEmail());
        user.setUserId(user_id);
        if(user_id > 0) {
            Boolean result = secretQuestionHandler.validateSecurityAnswer(user_id, user.getSecurityQuestion(), user.getSecurityAnswer());
            if(result == true)
            {
                return "redirect:/resetPassword";
            }
            else
            {
                model.addAttribute("statusMsg", "SECURITY DETAILS NOT MATCHED ");
                return "SecurityQuestion";
            }
        }
        else {
            model.addAttribute("statusMsg", "INVALID ACCOUNT");
            return "SecurityQuestion";
        }

    }
}
