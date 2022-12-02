package group11.EventFiesta.model;

public class Organizer {

    private String email;
    private String password;

    private Integer organizerId;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    @Override
    public String toString() {
        return "Organizer{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
