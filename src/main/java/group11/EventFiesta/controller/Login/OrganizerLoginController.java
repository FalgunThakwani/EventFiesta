package group11.EventFiesta.controller.Login;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.account.login.organizer.OrganizerLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OrganizerLoginController {

    @GetMapping("/organizerLogin")
    public String getOrganizerLogin(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("organizer", new Organizer());
        System.out.println("getOrganizerLogin: " + model.getAttribute("organizer"));
        return "OrganizerLogin";
    }

    @GetMapping("/organizerLogout")
    public String organizerLogout(Model model, HttpServletRequest request, HttpServletResponse response) {
        OrganizerLogin organizerLogin = new OrganizerLogin();
        organizerLogin.logout(request);
        model.addAttribute("organizer", new Organizer());
        return "OrganizerLogin";
    }
    @PostMapping("/handleOrganizerLogin")
    public String handleOrganizerLogin(Model model, @ModelAttribute Organizer organizer, HttpServletRequest request) {
        OrganizerLogin organizerLogin = new OrganizerLogin();
        System.out.println("handleOrganizerLogin: " + organizer);
        IState loginState = organizerLogin.login(organizer, request);
        model.addAttribute("statusMsg", loginState.getStatus());
        return loginState.getNextPage();
    }

}