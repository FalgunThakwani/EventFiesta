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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
public class UserResetPasswordController {
    @GetMapping("/userResetPassword")
    public String getResetPasswordPage(Model model, @ModelAttribute User user, @RequestParam("email") String email)
    {
        model.addAttribute("user", user);
        user.setEmail(email);
        return "UserResetPassword";
    }

    @PostMapping("/handleUserResetPassword")
    public String handleResetPassword(Model model, @ModelAttribute User user) throws Exception {
        model.addAttribute("user", user);
        String email = user.getEmail();
        String newPassword = user.getPassword();

        if(user.getPassword().equals(user.getConfirmPassword())) {
            Object[] params = new Object[]{"UserInfo", "user_id", "email", email};
            GenerateNewEncryptedPassword generateNewEncryptedPassword = new GenerateNewEncryptedPassword(new MySQLDBPersistence(), params);
            List<Map<String, Object>> result = generateNewEncryptedPassword.getID();
            if (result.size() > 0) {
                Map<String, Object> row = result.get(0);
                int user_id = (int) row.get("user_id");
                Object[] params1 = new Object[]{"UserSensitive", "private_key", "user_id", user_id};
                generateNewEncryptedPassword = new GenerateNewEncryptedPassword(new MySQLDBPersistence(), params1);
                String newEncryptedPassword = generateNewEncryptedPassword.getEncryptedPassword(newPassword);
                if (newEncryptedPassword.equals("FAILURE")) {
                    model.addAttribute("statusMsg", "PASSWORDS NOT UPDATED");
                } else {
                    Object[] params2 = new Object[]{"UserSensitive", "encrypted_password", newEncryptedPassword, "user_id", user_id};
                    IForgotPassword resetPasswordHandler = new ResetPasswordHandler(new MySQLDBPersistence(), params2);
                    IState state = resetPasswordHandler.validate(user);
                    model.addAttribute("statusMsg", state.getStatus());
                }
            }
            else{
                model.addAttribute("statusMsg", "FAILURE.. PLEASE TRY AGAIN");
            }
        }
        else
        {
            model.addAttribute("statusMsg", "PASSWORDS NOT MATCHING");
        }
        return "UserResetPassword";
    }
}
