package group11.EventFiesta.controller.Feedback;

import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.event.UserMyEventFeedback;
import group11.EventFiesta.model.Reviews;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@SessionAttributes("userReview")
@Controller
public class UserMyEventFeedbackController {

    @GetMapping("/userMyEventFeedback")
    public String getUserMyEventFeedback(Model model, @RequestParam("eventId") int eventID, @RequestParam("serviceId") int serviceId, @RequestParam("serviceType") String serviceType) throws Exception {
        int event_id = eventID;
        int service_id = serviceId;
        String service_type = serviceType;
        UserMyEventFeedback userMyEventFeedback = new UserMyEventFeedback(new MySQLDBPersistence());
        Reviews userReview = userMyEventFeedback.getUserMyEventServiceDetails(event_id, service_id, service_type);
        model.addAttribute("userReview", userReview);
        return "UserMyEventFeedback";
    }

    @PostMapping("/handleUserMyEventFeedback")
    public String handleUserMyEventFeedback(Model model, @ModelAttribute("userReview") Reviews userReview) throws Exception
    {
        int eventId = userReview.getEventId();
        int serviceId = userReview.getServiceId();
        float rating = userReview.getRating();
        String review = userReview.getReview();
        UserMyEventFeedback userMyEventFeedback = new UserMyEventFeedback(new MySQLDBPersistence());
        boolean result = userMyEventFeedback.submitUserMyEventFeedback(eventId, serviceId, rating, review);
        if(result == true)
        {
            model.addAttribute("statusMsg", "Thank you for your Feedback !!");
        }
        else
        {
            model.addAttribute("statusMsg", "Error in submitting Feedback. Please try Again !!");
        }
        return "UserMyEventFeedback";
    }
}
