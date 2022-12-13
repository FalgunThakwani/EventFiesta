package group11.EventFiesta.account.login.organizer;

import group11.EventFiesta.model.Account;

public class InvalidAccount extends LoginState {
    public InvalidAccount(Account account) {
        super(account);
        System.out.println("Invalid account");
    }
    @Override
    public void setStatus() {
        status = "Invalid account";
    }

    @Override
    public void setNextPage() {
        if(account.getOrg()) {
            nextPage = "OrganizerLogin";
        } else {
            nextPage = "UserLogin";
        }
    }
}
