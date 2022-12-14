package group11.EventFiesta.controller.EventGuestList;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.event.GuestListHandler;
import group11.EventFiesta.model.UserGuestList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserGuestListController {
    private IDBPersistence dbPersistence = new MySQLDBPersistence();

    @GetMapping("/user/{event_id}/guests")
    public String getGuestList(Model model, HttpServletRequest request, @PathVariable("event_id") int eventId) throws Exception {
        HttpSession session = request.getSession(false);
        int userId = (int) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        GuestListHandler glHandler = new GuestListHandler(dbPersistence);
        UserGuestList ugl = glHandler.loadGuestList(eventId);
        model.addAttribute("userGuestList", ugl);
        return "UserGuestList";
    }


}
