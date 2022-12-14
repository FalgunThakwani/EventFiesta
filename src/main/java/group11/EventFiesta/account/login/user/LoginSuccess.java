package group11.EventFiesta.account.login.user;

import group11.EventFiesta.model.Account;

public class LoginSuccess extends LoginState {

    LoginSuccess(Account account) {
        super(account);
    }

    @Override
    public void setStatus() {
        status = "Successfully Logged In !!";
    }

    @Override
    public void setNextPage() {
        nextPage = "redirect:/userHome";
    }

}
