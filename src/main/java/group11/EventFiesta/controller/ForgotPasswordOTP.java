package group11.EventFiesta.controller;

import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.organizer.ForgotPassword;
import group11.EventFiesta.organizer.GenerateOTP;
import group11.EventFiesta.organizer.LoginState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ForgotPasswordOTP {

    @GetMapping("/forgotPassword")
    public String getForgotPassword(Model model) {
        model.addAttribute("organizer", new Organizer());
        return "ForgotPassword";
    }

    @PostMapping("/getOTP")
    public String getOTP(Model model, @ModelAttribute Organizer organizer) {
        System.out.println("inside getOTP()" );
        GenerateOTP generateOTP = new GenerateOTP();
        LoginState loginState = generateOTP.generateOTP(organizer);
        return loginState.getNextPage();
    }

    @PostMapping("/validateOTP")
    public String validateOTP(Model model, @ModelAttribute Organizer organizer, HttpServletRequest request) {
        System.out.println("inside validateOTP()");
        ForgotPassword forgotPassword = new ForgotPassword();
        if(forgotPassword.validateOTP(organizer)) {
            return "redirect:/resetPassword";
        } else {
            return "redirect:/home";
        }
    }

}
