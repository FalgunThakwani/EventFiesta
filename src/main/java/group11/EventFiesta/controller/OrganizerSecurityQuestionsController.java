package group11.EventFiesta.controller;

import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.account.forgotpassword.securityquestion.SecretQuestionHandler;
import group11.EventFiesta.account.forgotpassword.securityquestion.VerifyEmailHandler;
import group11.EventFiesta.model.Organizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class OrganizerSecurityQuestionsController {
    @GetMapping("/organizerSecurityQuestion")
    public String getSecurityQuestionPage(Model model)
    {
        model.addAttribute("organizer", new Organizer());
        return "OrganizerSecurityQuestion";
    }

    @PostMapping("/handleOrganizerSecurityQuestion")
    public String handleSecurityQuestion(Model model, @ModelAttribute Organizer organizer) throws Exception
    {
        int organizer_id;
        String statusMessage;
        System.out.println("Entered email is : "+ organizer.getEmail());
        System.out.println("Selected security question is : "+ organizer.getSecurityQuestion());
        System.out.println("Entered security answer is : "+ organizer.getSecurityAnswer());
        String email = organizer.getEmail();
        Object[] params1 = new Object[]{"OrganizerInfo", "*", "email", email};
        VerifyEmailHandler verifyEmailHandler = new VerifyEmailHandler(new MySQLDBPersistence(), params1);
        List<Map<String, Object>> result = verifyEmailHandler.validateEmail(organizer);
        if(result != null && result.size() > 0) {

            organizer_id = Integer.parseInt(result.get(0).get("organizer_id").toString());
            System.out.println(organizer_id);
            String securityQuestion = organizer.getSecurityQuestion();
            String securityAnswer = organizer.getSecurityAnswer();
            Object[] params2 = new Object[]{"OrganizerSensitive", "*", "organizer_id", organizer_id};
            IForgotPassword secretQuestionHandler = new SecretQuestionHandler(new MySQLDBPersistence(), params2, securityQuestion, securityAnswer);
            IState state  = secretQuestionHandler.validate(organizer);
            model.addAttribute("statusMsg", state.getStatus());

            organizer.setOrganizerId(organizer_id);

            if(state.getStatus().equals("Security Details validated successfully!"))
            {
                model.addAttribute("organizer", organizer);
                return "redirect:/organizerResetPassword?email="+email;
            }
            else
            {
//                model.addAttribute("organizer", organizer);
                    return "OrganizerSecurityQuestion";
            }
        }
        else
        {
            model.addAttribute("statusMsg", "INVALID ACCOUNT");
            return "OrganizerSecurityQuestion";
        }
    }
}
