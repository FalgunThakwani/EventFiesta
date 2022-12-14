package group11.EventFiesta.controller.forgotpassword;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.account.forgotpassword.resetpassword.GenerateNewEncryptedPassword;
import group11.EventFiesta.account.forgotpassword.resetpassword.ResetPasswordHandler;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.model.Organizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class OrganizerResetPasswordController {
    @GetMapping("/organizerResetPassword")
    public String getResetPasswordPage(Model model, @ModelAttribute Organizer organizer, @RequestParam("email") String email)
    {
        System.out.println("In organizer ResetPassword " + email);
        model.addAttribute("organizer", organizer);
        organizer.setEmail(email);
        return "OrganizerResetPassword";
    }

    @PostMapping("/handleOrganizerResetPassword")
    public String handleResetPassword(Model model, @ModelAttribute Organizer organizer) throws Exception {
        model.addAttribute("organizer", organizer);
        String email = organizer.getEmail();
        String newPassword = organizer.getPassword();

        if(organizer.getPassword().equals(organizer.getConfirmPassword())) {
            Object[] params = new Object[]{"OrganizerInfo", "organizer_id", "email", email};
            GenerateNewEncryptedPassword generateNewEncryptedPassword = new GenerateNewEncryptedPassword(new MySQLDBPersistence(), params);
            List<Map<String, Object>> result = generateNewEncryptedPassword.getID();
            if (result.size() > 0) {
                Map<String, Object> row = result.get(0);
                int organizer_id = (int) row.get("organizer_id");
                Object[] params1 = new Object[]{"OrganizerSensitive", "private_key", "organizer_id", organizer_id};
                generateNewEncryptedPassword = new GenerateNewEncryptedPassword(new MySQLDBPersistence(), params1);
                String newEncryptedPassword = generateNewEncryptedPassword.getEncryptedPassword(newPassword);
                if (newEncryptedPassword.equals("FAILURE")) {
                    model.addAttribute("statusMsg", "PASSWORDS NOT UPDATED");
                } else {
                    Object[] params2 = new Object[]{"OrganizerSensitive", "encrypted_password", newEncryptedPassword, "organizer_id", organizer_id};
                    IForgotPassword resetPasswordHandler = new ResetPasswordHandler(new MySQLDBPersistence(), params2);
                    IState state = resetPasswordHandler.validate(organizer);
                    model.addAttribute("statusMsg", state.getStatus());
                }
            } else {
                    model.addAttribute("statusMsg", "FAILURE.. PLEASE TRY AGAIN");
            }
        }
        else
        {
            model.addAttribute("statusMsg", "PASSWORDS NOT MATCHING");
        }
        return "OrganizerResetPassword";
    }
}
