package group11.EventFiesta.organizer;

public class MailNotSent extends LoginState {
    @Override
    public void setLoginStatus() {
        loginStatus = "Mail could not be sent due to an error";
    }

    @Override
    public void setNextPage() {
        nextPage = "redirect:/home";
    }
}
