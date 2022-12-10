package group11.EventFiesta.controller;

import group11.EventFiesta.model.UserEventQuestionnaire;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes({ "userEventQuestionnaire" })
@Controller
public class UserEventExtendedDetailsController {

    @GetMapping("/addUserEventsExtended")
    public String getUserEventQuestionnaire(Model model) {
        model.addAttribute("userEventQuestionnaire", new UserEventQuestionnaire());
        return "UserEventExtendedDetails";
    }

    @PostMapping("/handleAddUserEventsExtended")
    public String handleUserEventQuestionnaireSubmission(@ModelAttribute UserEventQuestionnaire userEventQuestionnaire,
            Model model,
            RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("userEventObject", userEventQuestionnaire);
        model.addAttribute("userEventObject", userEventQuestionnaire);
        return "redirect:/bestOrganizers";
    }

}
