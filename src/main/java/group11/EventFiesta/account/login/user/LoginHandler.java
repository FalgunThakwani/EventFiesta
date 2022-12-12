package group11.EventFiesta.account.login.user;

public abstract class LoginHandler implements ILoginHandler {

    ILoginHandler nextHandler;

    @Override
    public void setNextHandler(ILoginHandler handler) {
        nextHandler = handler;
    }

}
