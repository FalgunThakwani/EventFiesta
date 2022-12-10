package group11.EventFiesta.organizer;

public class ValidatedOTP extends LoginState {
    @Override
    public void setLoginStatus() {
        loginStatus = "OTP validated succesfully!";
    }

    @Override
    public void setNextPage() {
        nextPage = "/resetPassword";
    }
}
