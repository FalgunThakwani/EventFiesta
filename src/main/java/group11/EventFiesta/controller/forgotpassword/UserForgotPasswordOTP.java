package group11.EventFiesta.controller.forgotpassword;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.account.*;
import group11.EventFiesta.account.forgotpassword.otp.ForgotPasswordUsingOTP;
import group11.EventFiesta.account.forgotpassword.otp.GenerateOTP;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserForgotPasswordOTP {

    @GetMapping("/UserForgotPasswordUsingOTP")
    public String getForgotPassword(Model model) {
        model.addAttribute("user", new User());
        return "UserForgotPasswordUsingOTP";
    }

    @PostMapping("/userGetOTP")
    public String getOTP(Model model, @ModelAttribute User user) {
        System.out.println("inside getOTP()" );
        IDBPersistence idbPersistence = new MySQLDBPersistence();
        List<Object[]> paramList = new ArrayList<>();
        Object [] params = new Object[] {"UserInfo", "user_id", "email", user.getEmail()};
        paramList.add(params);
        params = new Object[]{"UserSensitive", "user_id"};
        paramList.add(params);
        GenerateOTP generateOTP = new GenerateOTP(idbPersistence);
        IState accountState = generateOTP.generateOTP(user, paramList);
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
