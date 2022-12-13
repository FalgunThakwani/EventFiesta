package group11.EventFiesta.model;

public interface Account {

    boolean verifyEmailAddress();

    String getEmail();

    void setEmail(String email);

    String getPassword();

    Integer getAccountId();

    void setAccountId(Integer id);

    void setPassword(String password);
}
