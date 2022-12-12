package group11.EventFiesta.controller;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.account.forgotpassword.resetpassword.GenerateNewEncryptedPassword;
import group11.EventFiesta.account.forgotpassword.resetpassword.ResetPasswordHandler;
import group11.EventFiesta.model.Organizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"organizer"})
@Controller
public class OrganizerResetPasswordController {
    @GetMapping("/organizerResetPassword")
    public String getResetPasswordPage(Model model, @ModelAttribute Organizer organizer)
    {
        System.out.println("In organizer ResetPassword " + organizer.getOrganizerId());

        model.addAttribute("organizer", organizer);
        return "OrganizerResetPassword";
    }

    @PostMapping("/handleOrganizerResetPassword")
    public String handleResetPassword(Model model, @ModelAttribute Organizer organizer) throws Exception {
        model.addAttribute("organizer", organizer);
        System.out.println("organizer id is : " + organizer.getOrganizerId());
        System.out.println("organizer password is : " + organizer.getPassword());
        System.out.println("organizer confirm password is : " + organizer.getConfirmPassword());
        int organizer_id = organizer.getOrganizerId();
        String newPassword = organizer.getPassword();
        if(organizer.getPassword().equals(organizer.getConfirmPassword()))
        {
            IDBPersistence idbPersistence = new MySQLDBPersistence();
            Object [] params1 = new Object[] {"OrganizerSensitive", "private_key", "organizer_id", organizer_id};
            GenerateNewEncryptedPassword generateNewEncryptedPassword = new GenerateNewEncryptedPassword(new MySQLDBPersistence(), params1);
            String newEncryptedPassword = generateNewEncryptedPassword.getEncryptedPassword(newPassword);
            if(newEncryptedPassword.equals("FAILURE"))
            {
                 model.addAttribute("statusMsg", "PASSWORDS NOT UPDATED");
            }
            else {
                Object[] params2 = new Object[]{"OrganizerSensitive", "encrypted_password", newEncryptedPassword, "organizer_id", organizer_id};
                IForgotPassword resetPasswordHandler = new ResetPasswordHandler(new MySQLDBPersistence(), params2);
                IState state = resetPasswordHandler.validate(organizer);
                model.addAttribute("statusMsg", state.getStatus());
            }
        }
        else
        {
            model.addAttribute("statusMsg", "PASSWORDS NOT MATCHING");
        }
        return "OrganizerResetPassword";
    }
}
