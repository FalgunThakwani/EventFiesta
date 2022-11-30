package group11.EventFiesta.controller;

import group11.EventFiesta.model.Organizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrganizerLoginController {

    @GetMapping("/organizerLogin")
    public String getHomePage(Model model) {
        model.addAttribute("organizer", new Organizer());
        return "OrganizerLogin";
    }

    @PostMapping("/handleOrganizerLogin")
    public String handleOrganizerLogin(@ModelAttribute Organizer organizer) {
        System.out.println(organizer); //todo save to db
        return "home"; //todo redirect to  organizer landing page
    }

}