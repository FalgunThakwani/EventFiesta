package group11.EventFiesta.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.best5.GroupComponent;
import group11.EventFiesta.best5.HandleUserQuestionnaire;
import group11.EventFiesta.model.UserEventQuestionnaire;

@SessionAttributes({ "userEventQuestionnaire" })
@Controller
public class Best5Controller {
    private IDBPersistence dPersistence = new MySQLDBPersistence();

    @GetMapping("/bestOrganizers")
    public String getBest5Organizers(RedirectAttributes requestAttribute, Model model) {
        UserEventQuestionnaire questionnaire = (UserEventQuestionnaire) model.getAttribute("userEventObject");
        // UserEventQuestionnaire questionnaire = new UserEventQuestionnaire();
        // questionnaire.setBudget(100);
        // questionnaire.setCity("Halifax");
        // questionnaire.setService("Catering,Decoration");
        HandleUserQuestionnaire handleUserQuestionnaire = new HandleUserQuestionnaire(questionnaire, dPersistence);
        Map<String, List<GroupComponent>> servicesAndScores = handleUserQuestionnaire.getMapValuePairOfService();
        System.out.println(servicesAndScores.size());
        return "home";
    }

}
