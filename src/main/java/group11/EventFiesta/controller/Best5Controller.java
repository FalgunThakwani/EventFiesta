package group11.EventFiesta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.best5.*;
import group11.EventFiesta.event.EventManager;
import group11.EventFiesta.mail.Mail;
import group11.EventFiesta.mail.MailProtocol;
import group11.EventFiesta.mail.SSLSMTPProtocol;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group11.EventFiesta.model.UserEventQuestionnaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionAttributes({ "userEventQuestionnaire", "bestFiveOptions" })
@Controller
public class Best5Controller {

    @GetMapping("/bestOrganizers")
    public String getBest5Organizers(RedirectAttributes requestAttribute, Model model) {
         UserEventQuestionnaire questionnaire = (UserEventQuestionnaire)
         model.getAttribute("userEventObject");
        HandleUserQuestionnaire handleUserQuestionnaire = new HandleUserQuestionnaire(questionnaire);
        Map<String, List<GroupComponent>> servicesAndScores = handleUserQuestionnaire.getMapValuePairOfService();
        System.out.println(servicesAndScores);
        GetBestNOptions getBestNOptions = new GetBestNOptions();
        List<GroupComponent> bestFiveGroups = getBestNOptions.getBestNGroups(servicesAndScores, 5);
        for (int i = 0; i < bestFiveGroups.size(); i++) {
            bestFiveGroups.get(i).setId(i);
        }
        System.out.println("bestFiveGroups: " + bestFiveGroups);
        model.addAttribute("bestFiveOptions", bestFiveGroups);
        return "BestFiveOptions";
    }

    @PostMapping("/acceptOption")
    public String acceptOption(
            @SessionAttribute("userEventQuestionnaire") UserEventQuestionnaire userEventQuestionnaire,
            @SessionAttribute("bestFiveOptions") ArrayList<GroupComponent> bestFiveOptions,
            @RequestParam Integer optionId, HttpServletRequest request) {
        System.out.println("Inside acceptOption");
        System.out.println(userEventQuestionnaire);
        System.out.println(bestFiveOptions);
        if (bestFiveOptions.size() > optionId) {
            try {
                OrganizerGroup selectedGroup = (OrganizerGroup) bestFiveOptions.get(optionId);
                HttpSession session = request.getSession();
                Integer user_id = Integer.parseInt(session.getAttribute("accountId").toString());

                MailProtocol gmailSslSmtpProtocol = new SSLSMTPProtocol("smtp.gmail.com", 465);

                IDBPersistence idbPersistence = new MySQLDBPersistence();
                EventManager eventManager = new EventManager(idbPersistence, gmailSslSmtpProtocol);
                eventManager.addEvent(userEventQuestionnaire, selectedGroup, user_id);
            } catch (Exception exception) {
                System.out.println("Exception in acceptOption()");
            }
        }
        return "BestFiveOptions";
    }
}
