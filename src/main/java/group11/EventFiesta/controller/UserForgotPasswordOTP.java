package group11.EventFiesta.controller;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.account.*;
import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.account.forgotpassword.otp.ForgotPasswordUsingOTP;
import group11.EventFiesta.account.forgotpassword.otp.GenerateOTP;
import group11.EventFiesta.account.forgotpassword.otp.IForgotPassword;
import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserForgotPasswordOTP {

    @GetMapping("/userForgotPasswordWithOTP")
    public String getForgotPassword(Model model) {
        model.addAttribute("user", new User());
        return "ForgotPassword";
    }

    @PostMapping("/userGetOTP")
    public String getOTP(Model model, @ModelAttribute User user) {
        System.out.println("inside getOTP()" );
        IDBPersistence idbPersistence = new MySQLDBPersistence();
        Object [] params = new Object[] {"UserInfo", "user_id", "email", user.getEmail()};
        GenerateOTP generateOTP = new GenerateOTP(idbPersistence);
        IState accountState = generateOTP.generateOTP(user, params);
        return accountState.getNextPage();
    }

    @PostMapping("/userValidateOTP")
    public String validateOTP(Model model, @ModelAttribute User user) {
        System.out.println("inside validateOTP()");
        Object[] params = new Object[]{"UserSensitive", "otp, otp_time", "user_id", user.getAccountId().toString()};
        IForgotPassword forgotPassword = new ForgotPasswordUsingOTP(new MySQLDBPersistence(), params);
        IState state = forgotPassword.validate(user);
        model.addAttribute("statusMsg", state.getStatus());
        return state.getNextPage();
    }
}
