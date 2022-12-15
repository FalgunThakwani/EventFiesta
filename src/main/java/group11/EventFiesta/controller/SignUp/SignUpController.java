package group11.EventFiesta.controller.SignUp;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;

import group11.EventFiesta.model.Organizer;
import group11.EventFiesta.model.Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import group11.EventFiesta.model.User;
import group11.EventFiesta.signUp.ISignUpFactory;
import group11.EventFiesta.signUp.ISignup;
import group11.EventFiesta.signUp.SignUpFactory;

import java.util.ArrayList;

@Controller
public class SignUpController {

    private IDBPersistence dbPersistence = new MySQLDBPersistence();
    private ISignUpFactory factory = new SignUpFactory();

    @GetMapping("/signup")
    public String getHomePage(Model model) {
        model.addAttribute("user", new User());
        return "UserSignUp";
    }

    @PostMapping("/handleUserSignUp")
    public String handleUserSignUp(@ModelAttribute User user, Model model) {
        // Todo: Store User object in db
        ISignup signup = factory.createUserSignUp(dbPersistence);
        try {
            if (signup.validateUser(user)) {
                System.out.println("User with email " + user.getEmail() + " already exists.");
                System.out.println("Please try with different email address");
                model.addAttribute("ValidateMessage", "User with email " + user.getEmail()
                        + " already exists. Please try with different email address");
                return "UserSignUp";
            } else {
                signup.storeInfo(user);
                return "home";
            }
        } catch (Exception e) {
            System.out.println("Error in validating user");
            e.printStackTrace();
            return "/UserSignUp";
        }

    }

    @GetMapping("/org/signup")
    public String getOrgSignUpPage(Model model) {
        Organizer org = new Organizer();
        ArrayList<Service> services = new ArrayList<Service>();
        // for(int i=0; i<3; i++) {
        services.add(new Service("Catering", 0));
        services.add(new Service("Decoration", 0));
        services.add(new Service("Hall", 0));
        // }
        org.setService(services);
        model.addAttribute("organizer", org);
        return "OrganizerSignUp";
    }

    @PostMapping("/handleOrgSignUp")
    public String handleOrgSignUp(@ModelAttribute Organizer organizer, Model model) {
        System.out.println(organizer.getService());
        // Store Organizer object in db

        ISignup signup = factory.createOrganizerSignUp(dbPersistence);
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
