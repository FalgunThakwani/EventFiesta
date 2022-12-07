package group11.EventFiesta.controller;

import group11.EventFiesta.model.User;
import group11.EventFiesta.model.UserEventQuestionnaire;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes({"userEventQuestionnaire"})
@Controller
public class UserEventDetailsController {

    @GetMapping("/addUserEvents")
    public String getUserEventQuestionnaire(Model model) {
        model.addAttribute("userEventQuestionnaire", new UserEventQuestionnaire());
        return "UserEventDetails";
    }

    @PostMapping("/handleAddUserEvents")
    public String handleUserEventQuestionnaireSubmission(Model model,
                                                         @ModelAttribute UserEventQuestionnaire userEventQuestionnaire) {

        model.addAttribute("userEventQuestionnaire", userEventQuestionnaire);
        return "UserEventExtendedDetails";
    }

}
