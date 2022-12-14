package group11.EventFiesta.model;

public abstract class Account {

    Integer otp;
    Boolean isOrganizer = false;
    String secretQuestion;
    String secretAnswer;

    public abstract Boolean getIsOrganizer();

    public abstract void setIsOrganizer(Boolean isOrganizer) ;

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

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }


}
