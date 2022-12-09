package group11.EventFiesta.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserEventChecklist {

    @GetMapping("/userEventChecklist")
    public String getUserEventChecklist(Model model) {
        model.addAttribute("userEventChecklist", new UserEventChecklist());
        return "UserEventChecklist";
    }

    @PostMapping("/handleUserEventChecklist")
    public String handleUserEventQuestionnaireSubmission(Model model,
                                                         @ModelAttribute UserEventChecklist userEventChecklist) {

        model.addAttribute("userEventChecklist", userEventChecklist);
        return "UserEventChecklist";
    }

}
