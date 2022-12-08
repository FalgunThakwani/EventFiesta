package group11.EventFiesta.user;

public abstract class LoginHandler implements ILoginHandler{

    ILoginHandler nextHandler;

    @Override
    public void setNextHandler(ILoginHandler handler) {
        nextHandler = handler;
    }

}
