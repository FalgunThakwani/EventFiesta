package group11.EventFiesta.controller;

import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OrganizerEventDetailsController {

    @GetMapping("/getOrganizerDetails")
    public String getOrganizerDetails(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long organizerId = Long.parseLong(session.getAttribute("accountId").toString());
            String status = "Complete";
            IDBPersistence idbPersistence = new MySQLDBPersistence();
            Object[] params = new Object[]{organizerId, status};
            idbPersistence.loadData("getOrganizerEventDetails", params);
        } catch (Exception exception) {
            System.out.println("Exception in getOrganizerDetails: " + exception.getMessage());
        }
        return "OrganizerDetails";
    }

}
