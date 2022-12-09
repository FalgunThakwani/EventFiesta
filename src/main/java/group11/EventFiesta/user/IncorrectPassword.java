package group11.EventFiesta.user;

public class IncorrectPassword extends LoginState{

    @Override
    public void setLoginStatus() {
        loginStatus = "Password Incorrect !! Please enter Correct Password.";
    }

    @Override
    public void setNextHtml() {
        nextHtml = "UserLogin";
    }
}
