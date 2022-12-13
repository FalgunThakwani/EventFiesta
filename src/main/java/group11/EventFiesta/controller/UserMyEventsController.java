package group11.EventFiesta.controller;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.event.UserMyEvents;
import group11.EventFiesta.model.User;
import group11.EventFiesta.model.UserEvents;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller

public class UserMyEventsController {

    private IDBPersistence dbPersistence = new MySQLDBPersistence();

    @GetMapping("/user/myevents")
    public String getMyEvents(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("user", new User());
        HttpSession session = request.getSession(false);
        int userId = (int) session.getAttribute("userId");
        System.out.println(userId);
        model.addAttribute("userId", userId);
        UserMyEvents userMyEvents = new UserMyEvents(dbPersistence);
        ArrayList<UserEvents> events = userMyEvents.loadEvents(userId);
        model.addAttribute("events", events);
        return "UserMyEvents";
    }
}
