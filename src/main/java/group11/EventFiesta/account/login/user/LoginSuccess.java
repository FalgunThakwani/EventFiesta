package group11.EventFiesta.account.login.user;

public class LoginSuccess extends LoginState {
    @Override
    public void setLoginStatus() {
       loginStatus = "Successfully Logged In !!";
    }

    @Override
    public void setNextHtml() {
        nextHtml = "UserHomepage";
        //return "redirect:/resetPassword";
    }
}
