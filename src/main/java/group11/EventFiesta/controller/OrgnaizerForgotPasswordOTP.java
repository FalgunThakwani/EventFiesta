package group11.EventFiesta.controller;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.account.*;
import group11.EventFiesta.account.forgotpassword.AccountState;
import group11.EventFiesta.account.forgotpassword.otp.ForgotPasswordUsingOTP;
import group11.EventFiesta.account.forgotpassword.otp.GenerateOTP;
import group11.EventFiesta.account.forgotpassword.otp.IForgotPassword;
import group11.EventFiesta.model.Organizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrgnaizerForgotPasswordOTP {

    @GetMapping("/OrganizerForgotPassword")
    public String getForgotPassword(Model model) {
        model.addAttribute("organizer", new Organizer());
        return "OrganizerForgotPassword";
    }

    @PostMapping("/organizerGetOTP")
    public String getOTP(Model model, @ModelAttribute Organizer organizer) {
        System.out.println("inside getOTP()" );
        IDBPersistence idbPersistence = new MySQLDBPersistence();
        Object [] params = new Object[] {"OrganizerInfo", "organizer_id", "email", organizer.getEmail()};
        GenerateOTP generateOTP = new GenerateOTP(idbPersistence);
        IState accountState = generateOTP.generateOTP(organizer, params);
        return accountState.getNextPage();
    }

    @PostMapping("/organizerValidateOTP")
    public String validateOTP(Model model, @ModelAttribute Organizer organizer) {
        System.out.println("inside validateOTP()");
        Object[] params = new Object[]{"OrganizerSensitive", "otp, otp_time", "organizer_id", organizer.getAccountId().toString()};
        IForgotPassword forgotPassword = new ForgotPasswordUsingOTP(new MySQLDBPersistence(), params);
        IState state = forgotPassword.validate(organizer);
        model.addAttribute("statusMsg", state.getStatus());
        return state.getNextPage();
    }
}
