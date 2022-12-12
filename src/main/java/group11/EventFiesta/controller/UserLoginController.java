package group11.EventFiesta.controller;

import group11.EventFiesta.account.login.user.LoginState;
import group11.EventFiesta.account.login.user.UserLogin;
import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SessionAttributes({"user"})
@Controller
public class UserLoginController {

        @GetMapping("/userLogin")
        public String getUserLogin(Model model, HttpServletRequest request, HttpServletResponse response) {
            model.addAttribute("user", new User());
            System.out.println(request.getRequestURI());
            return "UserLogin";
        }

        @GetMapping("/userLogout")
        public String getUserLogout(Model model, HttpServletRequest request, HttpServletResponse response) {
            UserLogin userLogin = new UserLogin();
            userLogin.logout(request);
            model.addAttribute("user", new User());
            return "UserLogin";
         }

        @PostMapping("/handleUserLogin")
        public String handleUserLogin(Model model, @ModelAttribute User user, HttpServletRequest request) {
            model.addAttribute("user", user);
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            UserLogin userLogin = new UserLogin();
            LoginState loginState = userLogin.login(user, request);
            model.addAttribute("statusMsg", loginState.getLoginStatus());
            System.out.println(loginState.getLoginStatus());
            System.out.println(loginState.getNextHtml());
            return loginState.getNextHtml();
        }

}

