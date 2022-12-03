package group11.EventFiesta.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Organizer implements Account {

    private String email;
    private String password;

    private Integer organizerId;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAccountId() {
        return organizerId;
    }

    @Override
    public boolean verifyEmailAddress() {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }

    @Override
    public String toString() {
        return "Organizer{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
