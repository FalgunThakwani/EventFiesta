package group11.EventFiesta.controller;

import group11.EventFiesta.model.User;
import group11.EventFiesta.model.UserEventQuestionnaire;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes({"userEventQuestionnaire"})
@Controller
public class UserEventExtendedDetailsController {

    @GetMapping("/addUserEventsExtended")
    public String getUserEventQuestionnaire(Model model) {
        model.addAttribute("userEventQuestionnaire", new UserEventQuestionnaire());
        return "UserEventExtendedDetails";
    }

    @PostMapping("/handleAddUserEventsExtended")
    public String handleUserEventQuestionnaireSubmission(@ModelAttribute UserEventQuestionnaire userEventQuestionnaire) {
        System.out.println(userEventQuestionnaire.getEvent());
        System.out.println(userEventQuestionnaire.getProvince());
        System.out.println(userEventQuestionnaire.getCity());
        System.out.println(userEventQuestionnaire.getDateTime());
        System.out.println(userEventQuestionnaire.getService());
        System.out.println(userEventQuestionnaire.getBudget());
        System.out.println(userEventQuestionnaire.getGuestCount());
        System.out.println(userEventQuestionnaire.getEventArea());

        return "redirect:/bestOrganizers";
    }

}
