package group11.EventFiesta.controller;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.account.forgotpassword.resetpassword.GenerateNewEncryptedPassword;
import group11.EventFiesta.account.forgotpassword.resetpassword.ResetPasswordHandler;
import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"user"})
@Controller
public class UserResetPasswordController {
    @GetMapping("/userResetPassword")
    public String getResetPasswordPage(Model model, @ModelAttribute User user)
    {
        System.out.println("In userResetPassword " + user.getUserId());

        model.addAttribute("user", user);
        return "UserResetPassword";
    }

    @PostMapping("/handleUserResetPassword")
    public String handleResetPassword(Model model, @ModelAttribute User user) throws Exception {
        model.addAttribute("user", user);
        System.out.println("user id is : " + user.getUserId());
        System.out.println("user password is : " + user.getPassword());
        System.out.println("user confirm password is : " + user.getConfirmPassword());
        int user_id = user.getUserId();
        String newPassword = user.getPassword();
        if(user.getPassword().equals(user.getConfirmPassword()))
        {
            IDBPersistence idbPersistence = new MySQLDBPersistence();
            Object [] params1 = new Object[] {"UserSensitive", "private_key", "user_id", user_id};
            GenerateNewEncryptedPassword generateNewEncryptedPassword = new GenerateNewEncryptedPassword(new MySQLDBPersistence(), params1);
            String newEncryptedPassword = generateNewEncryptedPassword.getEncryptedPassword(newPassword);
            if(newEncryptedPassword.equals("FAILURE"))
            {
                 model.addAttribute("statusMsg", "PASSWORDS NOT UPDATED");
            }
            else {
                Object[] params2 = new Object[]{"UserSensitive", "encrypted_password", newEncryptedPassword, "user_id", user_id};
                IForgotPassword resetPasswordHandler = new ResetPasswordHandler(new MySQLDBPersistence(), params2);
                IState state = resetPasswordHandler.validate(user);
                model.addAttribute("statusMsg", state.getStatus());
            }
        }
        else
        {
            model.addAttribute("statusMsg", "PASSWORDS NOT MATCHING");
        }
        return "UserResetPassword";
    }
}
