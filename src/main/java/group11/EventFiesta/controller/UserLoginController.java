package group11.EventFiesta.controller;

import group11.EventFiesta.EncryptPassword;
import group11.EventFiesta.model.User;
import group11.EventFiesta.user.UserLogin;
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
        public String handleUserSignUp(@ModelAttribute User user) throws Exception {
            // Todo: logic for checking username and password
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            UserLogin userLogin = new UserLogin();
            userLogin.login(user.getEmail(), user.getPassword());


            return "UserLogin";
        }

    }

