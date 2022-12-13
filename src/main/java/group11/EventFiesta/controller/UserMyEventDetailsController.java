package group11.EventFiesta.controller;

import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.event.UserMyEventDetails;
import group11.EventFiesta.model.UserEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"userEvent"})
@Controller
public class UserMyEventDetailsController {

    @GetMapping("/userMyEventDetails")
    public String getUserMyEventDetails(Model model, @RequestParam("eventId") int eventID) throws Exception {
        int event_id = eventID;
        UserMyEventDetails userMyEventDetails = new UserMyEventDetails(new MySQLDBPersistence());
        UserEvent userEvent = userMyEventDetails.getUserMyEventDetails(event_id);
        model.addAttribute("userEvent", userEvent);

        return "UserMyEventDetails";
    }


}
