package group11.EventFiesta.controller;

import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.organizer.OrganizerLogin;
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
        System.out.println(request.getRequestURI());
        return "OrganizerLogin";
    }

    @GetMapping("/organizerLogout")
    public String organizerLogout(Model model, HttpServletRequest request, HttpServletResponse response) {
        OrganizerLogin organizerLogin = new OrganizerLogin();
        organizerLogin.logout(request);
        model.addAttribute("organizer", new Organizer());
        return "OrganizerLogin";
    }

    @GetMapping("/organizerDetails")
    public String getOrganizerDetails(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("organizer", new Organizer());
        System.out.println(request.getRequestURI());
        return "OrganizerDetails";
    }

    @PostMapping("/handleOrganizerLogin")
    public String handleOrganizerLogin(@ModelAttribute Organizer organizer, HttpServletRequest request) {
        System.out.println("inside handleOrganizerLogin()" );
        OrganizerLogin organizerLogin = new OrganizerLogin();
        if(organizerLogin.login(organizer, request))
            return "organizerDetails"; //todo redirect to  organizer landing page
        else {
            return "organizerLogin";
        }
    }

}