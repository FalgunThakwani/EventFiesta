package group11.EventFiesta.controller;

import group11.EventFiesta.ISignup;
import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.DBConnection.MySQLDBPersistence;
import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.model.Service;
import group11.EventFiesta.organizer.OrganizerSignUp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import group11.EventFiesta.model.User;
import group11.EventFiesta.user.UserSignUp;

import java.util.ArrayList;

@Controller
public class SignUpController {

    private IDBPersistence dbPersistence = new MySQLDBPersistence();

    @GetMapping("/signup")
    public String getHomePage(Model model) {
        model.addAttribute("user", new User());
        return "UserSignUp";
    }

    @PostMapping("/handleUserSignUp")
    public String handleUserSignUp(@ModelAttribute User user) {
        // Todo: Store User object in db
        ISignup signup = new UserSignUp(dbPersistence);
        try {
            if (signup.validateUser(user)) {
                // TODO: Send message to front end
                System.out.println("User with email " + user.getEmail() + " already exists.");
                System.out.println("Please try with different email address");
            } else {
                signup.storeInfo(user);
            }
        } catch (Exception e) {
            System.out.println("Error in validating user");
            e.printStackTrace();
        }
        return "home";
    }

    @GetMapping("/org/signup")
    public String getOrgSignUpPage(Model model) {
        Organizer org = new Organizer();
        ArrayList<Service> services= new ArrayList<Service>();
//        for(int i=0; i<3; i++) {
        services.add(new Service("Catering", 0));
        services.add(new Service("Decoration", 0));
        services.add(new Service("Hall", 0));
//        }
        org.setService(services);
        model.addAttribute("organizer", org);
        return "OrganizerSignUp";
    }

    @PostMapping("/handleOrgSignUp")
    public String handleOrgSignUp(@ModelAttribute Organizer organizer, Model model) {
        System.out.println(organizer.getService());
        // Store Organizer object in db
        ISignup signup = new OrganizerSignUp(dbPersistence);
        try {
            if (signup.validateUser(organizer)) {
                // Error
                System.out.println("Organizer already exist.");
                model.addAttribute("ValidationMsg", "Organizer already exists.");
                return "OrganizerSignUp";
            } else {
                signup.storeInfo(organizer);
            }
        } catch (Exception e) {
            System.out.println("Error in validating Organizer");
            e.printStackTrace();
        }
        
        return "home";
    }

}
