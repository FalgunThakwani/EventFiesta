package group11.EventFiesta.controller;

import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserEventExtendedDetailsController {

    @GetMapping("/addUserEventsExtended")
    public String getUserHomePage() {
        return "UserEventExtendedDetails";
    }

    @PostMapping("/handleAddUserEventsExtended")
    public String handleUserSignUp(@ModelAttribute User user) {
        // Todo: Store User object in db

        return "redirect:/bestOrganizers";
    }

}
