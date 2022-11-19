package group11.EventFiesta.controller;

import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

        @GetMapping("/userLogin")
        public String getHomePage(Model model) {
            model.addAttribute("user", new User());
            return "UserLogin";
        }

        @PostMapping("/handleUserLogin")
        public String handleUserSignUp(@ModelAttribute User user) {
            // Todo: logic for checking username and password
            System.out.println(user.getEmail());
            return "UserLogin";
        }

    }

