package group11.EventFiesta.organizer;

public class SentOTP extends LoginState {

    @Override
    public void setLoginStatus() {
        loginStatus = "OTP has been sent";
    }

    @Override
    public void setNextHtml() {
        nextPage = "enterOTP";
    }

}
