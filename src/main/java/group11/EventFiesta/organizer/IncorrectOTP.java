package group11.EventFiesta.organizer;

public class IncorrectOTP extends LoginState {
    @Override
    public void setLoginStatus() {
        loginStatus = "Incorrect OTP! Please try again..";
    }

    @Override
    public void setNextPage() {
        nextPage = "/enterOTP";
    }
}
