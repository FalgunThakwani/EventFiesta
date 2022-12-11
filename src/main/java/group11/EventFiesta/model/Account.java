package group11.EventFiesta.model;

public abstract class Account {

    Integer otp;

    Boolean org = false;

    public abstract Boolean getOrg();

    public abstract void setOrg(Boolean org) ;

    public abstract boolean verifyEmailAddress();

    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract String getPassword();

    public abstract Integer getAccountId();

    public abstract void setAccountId(Integer id);

    public abstract void setPassword(String password);

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }
}
