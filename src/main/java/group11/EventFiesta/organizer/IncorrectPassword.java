package group11.EventFiesta.organizer;

public class IncorrectPassword extends LoginState {

    @Override
    public void setLoginStatus() {
        loginStatus = "Entered password is incorrect";
    }

    @Override
    public void setNextHtml() {
        nextHtml = "organizerLogin";
    }
}
